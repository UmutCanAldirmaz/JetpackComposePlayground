package com.example.jetpackcomposeplayground.samples.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * [Item] verilerinin eklenmesi, güncellenmesi, silinmesi ve getirilmesini sağlayan Repository arayüzü.
 * Veri kaynağı detaylarını (Room, Ağ vb.) UI katmanından soyutlar.
 */
interface ItemsRepository {
    /**
     * Veri kaynağından tüm ürünleri akış ([Flow]) halinde getirir.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Belirtilen [id] ile eşleşen ürünü akış ([Flow]) halinde getirir.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Veri kaynağına yeni ürün ekler.
     */
    suspend fun insertItem(item: Item)

    /**
     * Veri kaynağından ürünü siler.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Veri kaynağındaki ürünü günceller.
     */
    suspend fun updateItem(item: Item)
}
