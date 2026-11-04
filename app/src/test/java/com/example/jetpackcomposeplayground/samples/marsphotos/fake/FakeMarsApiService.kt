package com.example.jetpackcomposeplayground.samples.marsphotos.fake

import com.example.jetpackcomposeplayground.samples.marsphotos.model.MarsPhoto
import com.example.jetpackcomposeplayground.samples.marsphotos.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}
