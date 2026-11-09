package com.example.jetpackcomposeplayground.samples.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room veritabanı sınıfı.
 * [Item] varlığını (Entity) barındırır ve veritabanı sürümü 1'dir.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    /**
     * Veritabanı işlemlerini gerçekleştirecek [ItemDao] erişim fonksiyonu.
     */
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        /**
         * Singleton deseniyle veritabanı örneğini (Instance) döner veya yoksa oluşturur.
         * Thread-safe (iş parçacığı güvenli) synchronized blok kullanır.
         *
         * @param context Uygulama bağlamı.
         */
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build().also { Instance = it }
            }
        }
    }
}
