package com.example.jetpackcomposeplayground.samples.inventory.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeplayground.samples.inventory.data.ItemsRepository

/**
 * [ItemsRepository] üzerinden tek bir ürünün detaylarını getiren, satan ve silen ViewModel.
 *
 * @param savedStateHandle Navigasyondan gelen `itemId` parametresine erişimi sağlar.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    @Suppress("unused")
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    companion object {
        @Suppress("unused")
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ürün detay ekranı (ItemDetailsScreen) için UI durum sınıfı.
 *
 * @property outOfStock Ürünün stokta kalıp kalmadığı bilgisi.
 * @property itemDetails Ürün detay verileri.
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)
