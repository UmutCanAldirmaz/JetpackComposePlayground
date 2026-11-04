package com.example.jetpackcomposeplayground.samples.marsphotos

import com.example.jetpackcomposeplayground.samples.marsphotos.data.NetworkMarsPhotosRepository
import com.example.jetpackcomposeplayground.samples.marsphotos.fake.FakeDataSource
import com.example.jetpackcomposeplayground.samples.marsphotos.fake.FakeMarsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}
