package com.example.jetpackcomposeplayground.samples.amphibians.data

import com.example.jetpackcomposeplayground.samples.amphibians.network.AmphibiansApiService
import com.example.jetpackcomposeplayground.samples.amphibians.model.Amphibian

/**
 * Amphibians verilerine erişim sağlayan Repository arayüzü.
 */
interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Retrofit servisini kullanarak verileri ağdan çeken somut Repository uygulaması.
 */
class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}