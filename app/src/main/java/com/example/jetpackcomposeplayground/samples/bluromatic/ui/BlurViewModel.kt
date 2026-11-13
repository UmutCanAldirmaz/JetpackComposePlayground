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

package com.example.jetpackcomposeplayground.samples.bluromatic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.WorkInfo
import com.example.jetpackcomposeplayground.PlaygroundApplication
import com.example.jetpackcomposeplayground.samples.bluromatic.KEY_IMAGE_URI
import com.example.jetpackcomposeplayground.samples.bluromatic.data.BlurAmountData
import com.example.jetpackcomposeplayground.samples.bluromatic.data.BluromaticRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Bu sınıf, Blur-O-Matic arayüzü ile WorkManager Repository katmanı arasındaki
 * durum yönetimini ve veri akışını sağlayan ViewModel sınıfıdır.
 */
class BlurViewModel(private val bluromaticRepository: BluromaticRepository) : ViewModel() {

    // UI tarafında radyo butonlarında gösterilecek olan blur seviyesi seçeneklerini tutar.
    internal val blurAmount = BlurAmountData.blurAmount

    // Arka plan işinin durumunu (Default, Loading, Complete) takip eden ve UI'a yayımlayan StateFlow nesnesidir.
    val blurUiState: StateFlow<BlurUiState> = bluromaticRepository.outputWorkInfo
        .map { info ->
            // WorkManager çıktısından kaydedilen resmin URI adresini okur.
            val outputImageUri = info.outputData.getString(KEY_IMAGE_URI)
            when {
                // İşlem bittiyse ve çıktı URI'si geçerliyse Complete durumuna geçer.
                info.state.isFinished && !outputImageUri.isNullOrEmpty() -> {
                    BlurUiState.Complete(outputUri = outputImageUri)
                }
                // İşlem iptal edildiyse varsayılan başlangıç durumuna dönülür.
                info.state == WorkInfo.State.CANCELLED -> {
                    BlurUiState.Default
                }
                // Diğer durumlarda (ENQUEUED, RUNNING) yükleniyor (Loading) durumu gösterilir.
                else -> BlurUiState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BlurUiState.Default
        )

    /**
     * Kullanıcı tarafından seçilen blur seviyesini alıp Repository üzerinden WorkManager zincirini başlatır.
     *
     * @param blurLevel Seçilen bulanıklık seviyesi.
     */
    fun applyBlur(blurLevel: Int) {
        bluromaticRepository.applyBlur(blurLevel)
    }

    /**
     * Arka planda yürütülen veya kuyruktaki WorkManager işini iptal etmek için Repository çağrısı yapar.
     */
    fun cancelWork() {
        bluromaticRepository.cancelWork()
    }

    /**
     * ViewModel bağımlılığı olan BluromaticRepository'yi PlaygroundApplication
     * konteynerinden alarak ViewModel'i oluşturan Factory yapısıdır.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val bluromaticRepository =
                    (this[APPLICATION_KEY] as PlaygroundApplication).bluromaticContainer.bluromaticRepository
                BlurViewModel(
                    bluromaticRepository = bluromaticRepository
                )
            }
        }
    }
}

/**
 * Blur-O-Matic ekranının mevcut durumlarını (Varsayılan, Yükleniyor, Tamamlandı) temsil eden Sealed Interface yapısıdır.
 */
sealed interface BlurUiState {
    object Default : BlurUiState
    object Loading : BlurUiState
    data class Complete(val outputUri: String) : BlurUiState
}
