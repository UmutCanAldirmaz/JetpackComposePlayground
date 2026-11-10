package com.example.jetpackcomposeplayground.samples.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Veritabanı erişim nesnesi (Data Access Object - DAO).
 * Veritabanı sorgularını ve CRUD (Ekleme, Güncelleme, Silme, Okuma) işlemlerini tanımlar.
 */
@Dao
interface ItemDao {

    /**
     * Yeni bir ürün ekler. Çakışma durumunda (Aynı ID) işlemi yoksayar.
     *
     * @param item Eklenecek ürün nesnesi.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Var olan bir ürünü günceller.
     *
     * @param item Güncellenecek ürün nesnesi.
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Bir ürünü veritabanından siler.
     *
     * @param item Silinecek ürün nesnesi.
     */
    @Delete
    suspend fun delete(item: Item)

    /**
     * Belirtilen ID'ye sahip ürünü veritabanından getirir.
     * Veri değiştiğinde otomatik güncellenen bir [Flow] döndürür.
     *
     * @param id Aranacak ürünün ID'si.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item?>

    /**
     * Tüm ürünleri alfabetik olarak isme göre sıralı şekilde getirir.
     * Veri değiştikçe UI'ı otomatik güncelleyen bir [Flow] yayınlar.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
