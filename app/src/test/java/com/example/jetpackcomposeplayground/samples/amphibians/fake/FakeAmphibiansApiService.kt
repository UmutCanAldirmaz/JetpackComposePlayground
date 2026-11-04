package com.example.jetpackcomposeplayground.samples.amphibians.fake

import com.example.jetpackcomposeplayground.samples.amphibians.model.Amphibian
import com.example.jetpackcomposeplayground.samples.amphibians.network.AmphibiansApiService

class FakeAmphibiansApiService : AmphibiansApiService {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibiansList
    }
}
