package com.example.jetpackcomposeplayground.samples.amphibians.network

import com.example.jetpackcomposeplayground.samples.amphibians.model.Amphibian
import retrofit2.http.GET

/**
 * Retrofit service interface for Amphibians API endpoint.
 */
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}
