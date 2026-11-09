package com.example.jetpackcomposeplayground.samples.inventory.data

import android.content.Context

/**
 * Manuel Bağımlılık Enjeksiyonu (Manual Dependency Injection) için kapsayıcı arayüzü.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

/**
 * [AppContainer] arayüzünün [OfflineItemsRepository] sağlayan somut gerçekleştirimi.
 *
 * @param context Veritabanını oluşturmak için gerekli uygulama bağlamı.
 */
class AppDataContainer(@Suppress("unused") private val context: Context) : AppContainer {
    /**
     * [ItemsRepository] için Room tabanlı çevrimdışı repository örneği (Lazy initialization).
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
