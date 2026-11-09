package com.example.jetpackcomposeplayground.samples.inventory.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * [ItemsRepository] arayüzünün Room veritabanı ([ItemDao]) kullanan çevrimdışı (offline) gerçekleştirimi.
 *
 * @property itemDao Veritabanı sorgularını yürüten DAO nesnesi.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /** Tüm ürün akışını Room DAO'dan alır. */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /** Tek bir ürün akışını Room DAO'dan alır. */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /** Ürünü Room veritabanına ekler. */
    override suspend fun insertItem(item: Item) = withContext(Dispatchers.IO) {
        itemDao.insert(item)
    }

    /** Ürünü Room veritabanından siler. */
    override suspend fun deleteItem(item: Item) = withContext(Dispatchers.IO) {
        itemDao.delete(item)
    }

    /** Ürünü Room veritabanında günceller. */
    override suspend fun updateItem(item: Item) = withContext(Dispatchers.IO) {
        itemDao.update(item)
    }
}
