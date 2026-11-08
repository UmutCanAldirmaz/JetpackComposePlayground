package com.example.jetpackcomposeplayground.samples.inventory.ui.home

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeplayground.samples.inventory.data.Item

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel : ViewModel() {
    companion object {
        @Suppress("unused")
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Item> = listOf())
