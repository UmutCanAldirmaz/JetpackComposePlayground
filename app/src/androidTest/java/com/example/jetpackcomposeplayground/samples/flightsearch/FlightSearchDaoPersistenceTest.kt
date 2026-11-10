package com.example.jetpackcomposeplayground.samples.flightsearch

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpackcomposeplayground.samples.flightsearch.data.Favorite
import com.example.jetpackcomposeplayground.samples.flightsearch.data.FlightSearchDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Veritabanının kapatılıp (destroy/close) tekrar açıldığında favori verilerinin
 * kalıcı olduğunu kontrol eden enstrümante test sınıfı.
 */
@RunWith(AndroidJUnit4::class)
class FlightSearchDaoPersistenceTest {

    @Test
    fun testFavoritePersistenceAcrossDbReopen() {
        runBlocking {
            val context: Context = ApplicationProvider.getApplicationContext()
            context.deleteDatabase("test_flight_search.db")

            // 1. Veritabanını asset dosyasından oluştur
            var db = Room.databaseBuilder(context, FlightSearchDatabase::class.java, "test_flight_search.db")
                .createFromAsset("database/flight_search.db")
                .allowMainThreadQueries()
                .build()

            val dao = db.flightSearchDao()
            dao.insertFavorite(Favorite(departureCode = "OPO", destinationCode = "SVO"))

            val favorites1 = dao.getAllFavorites().first()
            assertEquals(1, favorites1.size)
            assertEquals("OPO", favorites1[0].departureCode)
            assertEquals("SVO", favorites1[0].destinationCode)

            // 2. Veritabanı bağlantısını kapat (Process destroy simülasyonu)
            db.close()

            // 3. Veritabanını aynı disk dosyasından tekrar aç
            db = Room.databaseBuilder(context, FlightSearchDatabase::class.java, "test_flight_search.db")
                .createFromAsset("database/flight_search.db")
                .allowMainThreadQueries()
                .build()

            val daoReopened = db.flightSearchDao()
            val favoritesReopened = daoReopened.getAllFavorites().first()

            // 4. Kapatıp açıldıktan sonra verinin aynen durduğunu doğrula
            assertEquals(1, favoritesReopened.size)
            assertEquals("OPO", favoritesReopened[0].departureCode)
            assertEquals("SVO", favoritesReopened[0].destinationCode)

            db.close()
            context.deleteDatabase("test_flight_search.db")
        }
    }
}
