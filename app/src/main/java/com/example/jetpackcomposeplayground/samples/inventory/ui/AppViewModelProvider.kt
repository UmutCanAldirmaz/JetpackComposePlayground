package com.example.jetpackcomposeplayground.samples.inventory.ui

import androidx.lifecycle.ViewModelProvider
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
 * Tüm Inventory uygulaması genelinde ViewModel örneklerinin oluşturulması için Fabrika ([ViewModelProvider.Factory]) sağlar.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // ItemEditViewModel için fabrika ilklendiricisi
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }
        // ItemEntryViewModel için fabrika ilklendiricisi
        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        // ItemDetailsViewModel için fabrika ilklendiricisi
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }

        // HomeViewModel için fabrika ilklendiricisi
        initializer {
            HomeViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}

/**
 * [CreationExtras] üzerinden [PlaygroundApplication] nesnesine ve onun konteynırına erişen genişletme fonksiyonu.
 */
fun CreationExtras.inventoryApplication(): PlaygroundApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as PlaygroundApplication)


