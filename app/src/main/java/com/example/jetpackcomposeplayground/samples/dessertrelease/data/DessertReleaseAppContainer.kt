package com.example.jetpackcomposeplayground.samples.dessertrelease.data

import android.content.Context

/**
 * Dessert Release uygulaması için Manuel Bağımlılık Enjeksiyonu (Manual DI) Container Arayüzü.
 */
interface DessertReleaseAppContainer {
    val userPreferencesRepository: UserPreferencesRepository
}

/**
 * Gerçek (Production) bağımlılıkları ilklendiren varsayılan DI Container sınıfı.
 */
class DefaultDessertReleaseAppContainer(private val context: Context) : DessertReleaseAppContainer {
    override val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context.dataStore)
    }
}
