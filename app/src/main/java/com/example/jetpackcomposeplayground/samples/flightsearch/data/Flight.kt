package com.example.jetpackcomposeplayground.samples.flightsearch.data

/**
 * UI katmanına uçuş bilgilerini sunan domain model sınıfı.
 * Hem kalkış ve varış havalimanı detaylarını hem de uçuşun favorilere eklenip eklenmediğini tutar.
 */
data class Flight(
    val departureCode: String,
    val departureName: String,
    val destinationCode: String,
    val destinationName: String,
    val isFavorite: Boolean = false
)
