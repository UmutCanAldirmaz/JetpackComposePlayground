package com.example.jetpackcomposeplayground.samples.inventory.data

/**
 * Entity data class represents a single row in the database.
 */
data class Item(
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
