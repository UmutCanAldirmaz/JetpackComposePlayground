package com.example.jetpackcomposeplayground.samples.amphibians.ui

import com.example.jetpackcomposeplayground.samples.amphibians.model.Amphibian

/**
 * UI State for the Amphibians screen.
 */
sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}
