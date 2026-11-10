package com.example.jetpackcomposeplayground.samples.flightsearch.data

import android.content.Context

/**
 * Flight Search uygulaması için Manuel Bağımlılık Enjeksiyonu (Manual Dependency Injection) Container Arayüzü.
 * Uygulamanın ihtiyaç duyduğu repository bağımlılıklarını soyutlar.
 */
interface FlightSearchAppContainer {
    val flightSearchRepository: FlightSearchRepository
    val flightSearchPreferencesRepository: FlightSearchPreferencesRepository
}

/**
 * Gerçek (Production) bağımlılıkları ilklendiren varsayılan DI Container sınıfı.
 * Tembel ilklendirme (Lazy Initialization) kullanarak veritabanı ve repository örneklerini ihtiyaç duyulduğunda oluşturur.
 */
class DefaultFlightSearchAppContainer(private val context: Context) : FlightSearchAppContainer {

    /**
     * Room veritabanı örneği. İhtiyaç duyulduğunda diskten yüklenir.
     */
    private val database: FlightSearchDatabase by lazy {
        FlightSearchDatabase.getDatabase(context)
    }

    /**
     * Veritabanı DAO'su üzerinden iş mantığını yöneten ana repository.
     */
    override val flightSearchRepository: FlightSearchRepository by lazy {
        FlightSearchRepository(database.flightSearchDao())
    }

    /**
     * DataStore Preferences üzerinden arama metnini yöneten tercih repository'si.
     */
    override val flightSearchPreferencesRepository: FlightSearchPreferencesRepository by lazy {
        FlightSearchPreferencesRepository(context.flightSearchDataStore)
    }
}
