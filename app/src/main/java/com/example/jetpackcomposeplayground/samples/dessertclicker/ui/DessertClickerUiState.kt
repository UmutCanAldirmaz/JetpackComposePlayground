package com.example.jetpackcomposeplayground.samples.dessertclicker.ui

import com.example.jetpackcomposeplayground.samples.dessertclicker.data.Datasource

data class DessertClickerUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0
) {
    // Datasource'dan o anki indeksteki tatlıyı otomatik çeken akıllı özellikler (Computed properties):
    val currentDessertImageId: Int
        get() = Datasource.dessertList[currentDessertIndex].imageId

    val currentDessertPrice: Int
        get() = Datasource.dessertList[currentDessertIndex].price
}