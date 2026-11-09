package com.example.jetpackcomposeplayground.samples.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeplayground.samples.inventory.data.Item
import com.example.jetpackcomposeplayground.samples.inventory.data.ItemsRepository
import java.text.NumberFormat

/**
 * Room veritabanına yeni ürün eklemeyi ve veri girişlerinin doğrulanmasını yöneten ViewModel.
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Anlık ürün ekleme UI durumunu tutar.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Kullanıcının form alanlarına girdiği değerler değiştikçe UI durumunu günceller ve girdi doğrulamasını tetikler.
     *
     * @param itemDetails Güncellenmiş ürün detayları.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Form girdilerinin geçerli (boş olmadığını) kontrol eder.
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    /**
     * Form verisi geçerliyse veriyi Room veritabanına kaydeder.
     */
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }
}



/**
 * Bir ürün için UI Durumunu temsil eder.
 *
 * @property itemDetails Ürüne ait form alan verileri.
 * @property isEntryValid Form girdilerinin kaydetmek için geçerli olup olmadığı.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

/**
 * Form alanlarındaki String türündeki değerleri tutan veri sınıfı.
 */
data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * [ItemDetails] nesnesini veritabanı varlığı olan [Item] nesnesine dönüştüren genişletme fonksiyonu.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

/**
 * Fiyatı yerel para birimi formatında String olarak döndüren genişletme fonksiyonu.
 */
fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * [Item] veritabanı nesnesini [ItemUiState] nesnesine dönüştüren genişletme fonksiyonu.
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * [Item] veritabanı nesnesini form verisi olan [ItemDetails] nesnesine dönüştüren genişletme fonksiyonu.
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)

