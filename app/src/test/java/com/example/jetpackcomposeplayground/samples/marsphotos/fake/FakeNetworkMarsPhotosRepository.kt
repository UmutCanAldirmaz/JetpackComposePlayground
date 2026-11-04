package com.example.jetpackcomposeplayground.samples.marsphotos.fake

import com.example.jetpackcomposeplayground.samples.marsphotos.data.MarsPhotosRepository
import com.example.jetpackcomposeplayground.samples.marsphotos.model.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}
