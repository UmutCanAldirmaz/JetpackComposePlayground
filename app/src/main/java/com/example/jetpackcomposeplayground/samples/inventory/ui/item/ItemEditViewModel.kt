package com.example.jetpackcomposeplayground.samples.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeplayground.samples.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * [ItemsRepository] üzerinden var olan bir ürünü güncelleyen ViewModel.
 *
 * @param savedStateHandle Navigasyondan gelen `itemId` parametresine erişimi sağlar.
 * @param itemsRepository Ürün veri deposu.
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    /**
     * Güncellenecek ürünün mevcut UI durumunu tutar.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            itemUiState = itemsRepository.getItemStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(isEntryValid = true)
        }
    }

    /**
     * Kullanıcı arayüzünde girilen yeni değerlerle UI durumunu günceller ve doğrular.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Var olan ürünü veritabanında günceller.
     */
    suspend fun updateItem() {
        if (validateInput(itemUiState.itemDetails)) {
            itemsRepository.updateItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            val priceVal = price.toDoubleOrNull()
            val qtyVal = quantity.toIntOrNull()
            name.isNotBlank() && priceVal != null && priceVal >= 0.0 && qtyVal != null && qtyVal >= 0
        }
    }
}
