package com.example.jetpackcomposeplayground.samples.inventory.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jetpackcomposeplayground.PlaygroundApplication
import com.example.jetpackcomposeplayground.samples.inventory.ui.home.HomeViewModel
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemDetailsViewModel
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemEditViewModel
import com.example.jetpackcomposeplayground.samples.inventory.ui.item.ItemEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle()
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel()
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle()
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel()
        }
    }
}

/**
 * Extension function to queries for [PlaygroundApplication] object.
 */
fun CreationExtras.playgroundApplication(): PlaygroundApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PlaygroundApplication)
