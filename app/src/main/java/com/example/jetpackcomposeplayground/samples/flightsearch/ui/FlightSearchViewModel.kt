package com.example.jetpackcomposeplayground.samples.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jetpackcomposeplayground.PlaygroundApplication
import com.example.jetpackcomposeplayground.samples.flightsearch.data.Airport
import com.example.jetpackcomposeplayground.samples.flightsearch.data.FlightSearchPreferencesRepository
import com.example.jetpackcomposeplayground.samples.flightsearch.data.FlightSearchRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Flight Search ekranının UI durumunu ve veri akışlarını yöneten ViewModel.
 */
class FlightSearchViewModel(
    private val repository: FlightSearchRepository,
    private val preferencesRepository: FlightSearchPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlightSearchUiState())
    val uiState: StateFlow<FlightSearchUiState> = _uiState.asStateFlow()

    private var flightsJob: Job? = null
    private var suggestionsJob: Job? = null

    init {
        // 1. Favori rotaları Room veritabanından reaktif olarak sürekli dinle
        viewModelScope.launch {
            repository.getFavoriteFlights().collect { favorites ->
                _uiState.update { it.copy(favoriteFlights = favorites) }
            }
        }

        // 2. DataStore'da kayıtlı olan son arama metnini ilk açılışta yükle
        viewModelScope.launch {
            val initialSavedText = preferencesRepository.searchText.first()
            if (initialSavedText.isNotBlank()) {
                _uiState.update { it.copy(searchQuery = initialSavedText) }
                val matchedAirport = repository.getAirportByCode(initialSavedText)
                if (matchedAirport != null) {
                    selectAirport(matchedAirport, saveToDataStore = false)
                } else {
                    updateAutocompleteSuggestions(initialSavedText)
                }
            }
        }
    }

    /**
     * Kullanıcı arama metnini değiştirdiğinde tetiklenir.
     */
    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query,
                selectedAirport = if (query != it.selectedAirport?.iataCode) null else it.selectedAirport
            )
        }

        viewModelScope.launch {
            preferencesRepository.saveSearchText(query)
        }

        if (query.isBlank()) {
            flightsJob?.cancel()
            suggestionsJob?.cancel()
            _uiState.update {
                it.copy(
                    autocompleteSuggestions = emptyList(),
                    flightsList = emptyList(),
                    selectedAirport = null
                )
            }
        } else {
            updateAutocompleteSuggestions(query)
        }
    }

    /**
     * Otomatik tamamlama listesinden bir havalimanı seçildiğinde tetiklenir.
     */
    fun selectAirport(airport: Airport, saveToDataStore: Boolean = true) {
        _uiState.update {
            it.copy(
                selectedAirport = airport,
                searchQuery = airport.iataCode,
                autocompleteSuggestions = emptyList()
            )
        }

        if (saveToDataStore) {
            viewModelScope.launch {
                preferencesRepository.saveSearchText(airport.iataCode)
            }
        }

        loadFlightsFromDeparture(airport)
    }

    private fun updateAutocompleteSuggestions(query: String) {
        suggestionsJob?.cancel()
        suggestionsJob = viewModelScope.launch {
            repository.getAutocompleteSuggestions(query).collect { suggestions ->
                _uiState.update { it.copy(autocompleteSuggestions = suggestions) }
            }
        }
    }

    private fun loadFlightsFromDeparture(airport: Airport) {
        flightsJob?.cancel()
        flightsJob = viewModelScope.launch {
            repository.getFlightsFromDeparture(airport).collect { flights ->
                _uiState.update { it.copy(flightsList = flights) }
            }
        }
    }

    /**
     * Bir uçuş rotasını favorilere ekler veya favorilerden çıkarır.
     */
    fun toggleFavorite(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            repository.toggleFavorite(departureCode, destinationCode)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PlaygroundApplication)
                val container = application.flightSearchContainer
                FlightSearchViewModel(
                    repository = container.flightSearchRepository,
                    preferencesRepository = container.flightSearchPreferencesRepository
                )
            }
        }
    }
}
