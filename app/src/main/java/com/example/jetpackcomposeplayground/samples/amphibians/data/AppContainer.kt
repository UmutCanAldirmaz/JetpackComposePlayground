package com.example.jetpackcomposeplayground.samples.amphibians.data

import com.example.jetpackcomposeplayground.samples.amphibians.network.AmphibiansApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/**
 * Uygulamanın bağımlılıklarını sağlayan konteyner arayüzü.
 */
interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}

/**
 * Retrofit ve Repository bağımlılıklarını 'lazy' olarak oluşturan varsayılan konteyner.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        DefaultAmphibiansRepository(retrofitService)
    }
}