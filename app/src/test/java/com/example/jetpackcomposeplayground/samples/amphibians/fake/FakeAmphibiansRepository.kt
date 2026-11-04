package com.example.jetpackcomposeplayground.samples.amphibians.fake

import com.example.jetpackcomposeplayground.samples.amphibians.data.AmphibiansRepository
import com.example.jetpackcomposeplayground.samples.amphibians.model.Amphibian

class FakeAmphibiansRepository : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibiansList
    }
}
