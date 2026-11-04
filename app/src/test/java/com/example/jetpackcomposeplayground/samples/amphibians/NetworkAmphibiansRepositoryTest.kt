package com.example.jetpackcomposeplayground.samples.amphibians

import com.example.jetpackcomposeplayground.samples.amphibians.data.DefaultAmphibiansRepository
import com.example.jetpackcomposeplayground.samples.amphibians.fake.FakeAmphibiansApiService
import com.example.jetpackcomposeplayground.samples.amphibians.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkAmphibiansRepositoryTest {

    @Test
    fun defaultAmphibiansRepository_getAmphibians_verifyAmphibianList() = runTest {
        val repository = DefaultAmphibiansRepository(
            amphibiansApiService = FakeAmphibiansApiService()
        )
        assertEquals(FakeDataSource.amphibiansList, repository.getAmphibians())
    }
}
