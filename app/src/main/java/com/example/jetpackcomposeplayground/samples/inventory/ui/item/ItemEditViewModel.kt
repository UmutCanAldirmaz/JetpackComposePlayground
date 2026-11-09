package com.example.jetpackcomposeplayground.samples.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeplayground.samples.inventory.data.ItemsRepository

/**
 * [ItemsRepository] üzerinden var olan bir ürünü güncelleyen ViewModel.
 *
 * @param savedStateHandle Navigasyondan gelen `itemId` parametresine erişimi sağlar.
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /**
     * Güncellenecek ürünün mevcut UI durumunu tutar.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    @Suppress("unused")
    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    @Suppress("unused")
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
