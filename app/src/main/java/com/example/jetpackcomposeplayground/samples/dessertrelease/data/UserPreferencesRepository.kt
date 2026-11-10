package com.example.jetpackcomposeplayground.samples.dessertrelease.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// DataStore dosyasının cihazdaki adı (layout_preferences.preferences_pb olarak saklanır)
private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"

/**
 * [Context] uzantısı (Extension property) olarak tanımlanan Preferences DataStore delegasyonu.
 * `by preferencesDataStore` delegasyonu, uygulama genelinde bu isimle TEK BİR (Singleton) DataStore örneği
 * oluşturulmasını garanti eder ve thread-safe bir yapı sunar.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)

/**
 * Kullanıcı tercihlerini (düzen seçimini: Grid / Linear) DataStore Preferences kullanarak
 * yerel hafızada okuma ve yazma işlemlerinden sorumlu Repository sınıfı.
 *
 * @param dataStore Tercihlerin yazılıp okunacağı Preferences DataStore nesnesi.
 */
class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        // DataStore içindeki boolean tipindeki anahtarımız ("is_linear_layout")
        val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")
        const val TAG = "UserPreferencesRepo"
    }

    /**
     * Kullanıcının seçtiği düzen tercihini (Linear: true, Grid: false) DataStore'a asenkron olarak kaydeder.
     * [edit] metodu askıya alınabilir (suspend) bir fonksiyondur ve Coroutine içerisinde çağrılmalıdır.
     */
    suspend fun saveLayoutPreference(isLinearLayout: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT] = isLinearLayout
        }
    }

    /**
     * DataStore'dan düzen tercihini okuyan reaktif [Flow] akışı.
     * - [catch]: Dosya okuma sırasında bir [IOException] oluşursa hatayı yakalar ve boş tercihler ([emptyPreferences]) yayar.
     * - [map]: Tercihler nesnesinden [IS_LINEAR_LAYOUT] anahtarına ait değeri alır. Eğer henüz bir seçim yapılmamışsa varsayılan olarak `true` (Linear) döner.
     */
    val isLinearLayout: Flow<Boolean> = dataStore.data
        .catch { exception ->
            // Dosya okuma hatası durumunda uygulamanın çökmesini önler
            if (exception is IOException) {
                Log.e(TAG, "Preferences verisi okunurken hata oluştu.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            // Anahtar mevcut değilse varsayılan değer true (Linear Layout)
            preferences[IS_LINEAR_LAYOUT] ?: true
        }
}
