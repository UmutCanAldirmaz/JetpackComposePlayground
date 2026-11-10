package com.example.jetpackcomposeplayground.samples.flightsearch.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

/**
 * Uçuş arama ve favoriler işlemlerinin iş mantığını yöneten Repository sınıfı.
 */
class FlightSearchRepository(
    private val flightSearchDao: FlightSearchDao
) {
    /**
     * Arama metnine göre havalimanı otomatik tamamlama önerilerini getirir.
     */
    fun getAutocompleteSuggestions(query: String): Flow<List<Airport>> {
        if (query.isBlank()) return flowOf(emptyList())
        return flightSearchDao.getAirportsBySearch(query.trim())
    }

    /**
     * Seçilen kalkış havalimanından diğer tüm havalimanlarına olan uçuş listesini oluşturur
     * ve favori durumlarını birleştirerek reaktif Flow olarak sunar.
     */
    fun getFlightsFromDeparture(departureAirport: Airport): Flow<List<Flight>> {
        val destinationAirportsFlow = flightSearchDao.getAllAirportsExcept(departureAirport.iataCode)
        val favoritesFlow = flightSearchDao.getAllFavorites()

        return combine(destinationAirportsFlow, favoritesFlow) { destinations, favorites ->
            destinations.map { dest ->
                val isFav = favorites.any {
                    it.departureCode == departureAirport.iataCode && it.destinationCode == dest.iataCode
                }
                Flight(
                    departureCode = departureAirport.iataCode,
                    departureName = departureAirport.name,
                    destinationCode = dest.iataCode,
                    destinationName = dest.name,
                    isFavorite = isFav
                )
            }
        }
    }

    /**
     * Kayıtlı tüm favori rotaları havalimanı isimleriyle eşleştirerek getirir.
     */
    fun getFavoriteFlights(): Flow<List<Flight>> {
        val airportsFlow = flightSearchDao.getAllAirports()
        val favoritesFlow = flightSearchDao.getAllFavorites()

        return combine(airportsFlow, favoritesFlow) { airports, favorites ->
            val airportMap = airports.associateBy { it.iataCode }
            favorites.map { fav ->
                val depAirport = airportMap[fav.departureCode]
                val destAirport = airportMap[fav.destinationCode]
                Flight(
                    departureCode = fav.departureCode,
                    departureName = depAirport?.name ?: fav.departureCode,
                    destinationCode = fav.destinationCode,
                    destinationName = destAirport?.name ?: fav.destinationCode,
                    isFavorite = true
                )
            }
        }
    }

    /**
     * IATA koduna göre tek bir havalimanı getirir.
     */
    suspend fun getAirportByCode(code: String): Airport? {
        return flightSearchDao.getAirportByCode(code)
    }

    /**
     * Rota favorilerde varsa kaldırır, yoksa ekler.
     */
    suspend fun toggleFavorite(departureCode: String, destinationCode: String) {
        val existing = flightSearchDao.getFavorite(departureCode, destinationCode)
        if (existing != null) {
            flightSearchDao.deleteFavorite(departureCode, destinationCode)
        } else {
            flightSearchDao.insertFavorite(
                Favorite(departureCode = departureCode, destinationCode = destinationCode)
            )
        }
    }
}
