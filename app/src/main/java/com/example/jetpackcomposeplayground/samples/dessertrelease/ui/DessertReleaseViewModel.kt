/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jetpackcomposeplayground.samples.dessertrelease.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jetpackcomposeplayground.PlaygroundApplication
import com.example.jetpackcomposeplayground.R
import com.example.jetpackcomposeplayground.samples.dessertrelease.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Dessert Release bileşenlerinin durumunu (State) ve kullanıcı tercihlerini (DataStore) yöneten ViewModel.
 *
 * @param userPreferencesRepository DataStore okuma ve yazma işlemlerinden sorumlu repository bağımlılığı.
 */
class DessertReleaseViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    /**
     * UI Durumu (StateFlow):
     * 1. [userPreferencesRepository.isLinearLayout]: DataStore'dan gelen soğuk (cold) Flow<Boolean> akışıdır.
     * 2. [.map]: Gelen boolean veriyi [DessertReleaseUiState] nesnesine dönüştürür.
     * 3. [.stateIn]: Soğuk Flow'u UI için her an hazır sıcak (hot) StateFlow'a çevirir.
     *    - [scope = viewModelScope]: Akışın yaşam döngüsünü ViewModel'e bağlar.
     *    - [started = SharingStarted.WhileSubscribed(5_000)]: UI görünür olduğu sürece dinler.
     *      Ekran döndürüldüğünde (configuration change) veya geçici arka plana atıldığında 5 saniye tolerans tanır.
     *    - [initialValue = DessertReleaseUiState()]: İlk veriler yüklenene kadar gösterilecek varsayılan durum.
     */
    val uiState: StateFlow<DessertReleaseUiState> =
        userPreferencesRepository.isLinearLayout.map { isLinearLayout ->
            DessertReleaseUiState(isLinearLayout)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DessertReleaseUiState()
        )

    /**
     * Kullanıcı düzen butonuna bastığında (Linear / Grid değişimi) çağrılır.
     * DataStore yazma işlemi bir [suspend] fonksiyon olduğu için [viewModelScope.launch] ile
     * yeni bir coroutine başlatılarak kaydedilir.
     *
     * @param isLinearLayout Seçilen düzen türü (true: Linear, false: Grid).
     */
    fun selectLayout(isLinearLayout: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.saveLayoutPreference(isLinearLayout)
        }
    }

    /**
     * ViewModel nesnesinin parametreli olarak ilklendirilebilmesini sağlayan Factory tanımı.
     * Manuel Bağımlılık Enjeksiyonu (Manual DI) kullanarak [PlaygroundApplication] üzerinden
     * [userPreferencesRepository] örneğine erişir ve ViewModel'e aktarır.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Application bağımlılığına erişip repository'yi ViewModel constructor'ına geçiyoruz
                val application = (this[APPLICATION_KEY] as PlaygroundApplication)
                DessertReleaseViewModel(application.dessertReleaseContainer.userPreferencesRepository)
            }
        }
    }
}

/**
 * Dessert Release ekranının UI durum verilerini tutan veri sınıfı (State Holder).
 *
 * @param isLinearLayout Düzenin Linear mı (true) yoksa Grid mi (false) olduğunu belirtir.
 * @param toggleContentDescription Erişilebilirlik (Accessibility) için ikon açıklama metni kaynağı.
 * @param toggleIcon Sağ üstteki butonun göstereceği ikon kaynağı (Grid veya Linear ikonu).
 */
data class DessertReleaseUiState(
    val isLinearLayout: Boolean = true,
    val toggleContentDescription: Int =
        if (isLinearLayout) R.string.grid_layout_toggle else R.string.linear_layout_toggle,
    val toggleIcon: Int =
        if (isLinearLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout
)
