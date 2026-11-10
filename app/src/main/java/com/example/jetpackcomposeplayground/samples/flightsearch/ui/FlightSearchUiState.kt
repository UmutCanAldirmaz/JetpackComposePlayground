package com.example.jetpackcomposeplayground.samples.flightsearch.ui

import com.example.jetpackcomposeplayground.samples.flightsearch.data.Airport
import com.example.jetpackcomposeplayground.samples.flightsearch.data.Flight

/**
 * Flight Search ekranının UI durumunu tutan veri sınıfı.
 */
data class FlightSearchUiState(
    val searchQuery: String = "",
    val selectedAirport: Airport? = null,
    val autocompleteSuggestions: List<Airport> = emptyList(),
    val flightsList: List<Flight> = emptyList(),
    val favoriteFlights: List<Flight> = emptyList()
)
