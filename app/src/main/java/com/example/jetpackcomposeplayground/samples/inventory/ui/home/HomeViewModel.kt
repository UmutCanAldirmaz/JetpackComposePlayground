package com.example.jetpackcomposeplayground.samples.inventory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeplayground.samples.inventory.data.Item
import com.example.jetpackcomposeplayground.samples.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Room veritabanındaki tüm ürünleri çekmek ve UI durumunu yönetmek için kullanılan ViewModel.
 */
class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    companion object {
        @Suppress("unused")
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}

/**
 * Ana ekran (HomeScreen) için UI Durum veri sınıfı.
 *
 * @property itemList Ekranda listelenecek ürün dizisi.
 */
data class HomeUiState(val itemList: List<Item> = listOf())
