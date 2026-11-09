package com.example.jetpackcomposeplayground.samples.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room veritabanında "items" tablosundaki tek bir satırı (ürünü) temsil eden Varlık (Entity) veri sınıfı.
 *
 * @property id Her ürün için benzersiz birincil anahtar (otomatik artan).
 * @property name Ürün adı.
 * @property price Ürün birim fiyatı.
 * @property quantity Stoktaki ürün miktarı.
 */
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
