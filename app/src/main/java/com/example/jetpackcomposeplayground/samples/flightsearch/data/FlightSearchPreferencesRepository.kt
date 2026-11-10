package com.example.jetpackcomposeplayground.samples.flightsearch.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val FLIGHT_SEARCH_PREFERENCE_NAME = "flight_search_preferences"

/**
 * [Context] uzantısı (Extension property) olarak Preferences DataStore delegasyonu.
 */
val Context.flightSearchDataStore: DataStore<Preferences> by preferencesDataStore(
    name = FLIGHT_SEARCH_PREFERENCE_NAME
)

/**
 * Kullanıcının son arama metnini DataStore Preferences kullanarak yerel hafızada saklayan Repository.
 */
class FlightSearchPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val SEARCH_TEXT = stringPreferencesKey("search_text")
        const val TAG = "FlightSearchPrefRepo"
    }

    /**
     * Saklanan arama metnini reaktif olarak sunar.
     */
    val searchText: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Preferences verisi okunurken hata oluştu.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[SEARCH_TEXT] ?: ""
        }

    /**
     * Kullanıcının girdiği arama metnini DataStore'a kaydeder.
     */
    suspend fun saveSearchText(text: String) {
        dataStore.edit { preferences ->
            preferences[SEARCH_TEXT] = text
        }
    }
}
