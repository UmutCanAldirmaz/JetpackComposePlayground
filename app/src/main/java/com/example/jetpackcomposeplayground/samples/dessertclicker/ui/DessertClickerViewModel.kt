package com.example.jetpackcomposeplayground.samples.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeplayground.samples.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertClickerUiState())
    val uiState: StateFlow<DessertClickerUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val newRevenue = currentState.revenue + currentState.currentDessertPrice
            val newDessertsSold = currentState.dessertsSold + 1
            val nextIndex = determineDessertIndex(newDessertsSold)

            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold,
                currentDessertIndex = nextIndex
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        val desserts = Datasource.dessertList
        var dessertIndex = 0
        for (index in desserts.indices) {
            if (dessertsSold >= desserts[index].startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }
        return dessertIndex
    }
}
