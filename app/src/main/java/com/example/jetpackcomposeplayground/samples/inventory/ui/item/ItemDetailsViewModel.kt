package com.example.jetpackcomposeplayground.samples.inventory.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeplayground.samples.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * [ItemsRepository] üzerinden tek bir ürünün detaylarını getiren, satan ve silen ViewModel.
 *
 * @param savedStateHandle Navigasyondan gelen `itemId` parametresine erişimi sağlar.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    @Suppress("unused")
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    companion object {
        @Suppress("unused")
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val uiState: StateFlow<ItemDetailsUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(it.quantity <= 0,itemDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentItem = uiState.value.itemDetails.toItem()
            if (currentItem.quantity > 0) {
                itemsRepository.updateItem(currentItem.copy(quantity = currentItem.quantity - 1))
            }
        }
    }

    /**
     * Seçili ürünü veritabanından siler.
     */
    suspend fun deleteItem() {
        itemsRepository.deleteItem(uiState.value.itemDetails.toItem())
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
