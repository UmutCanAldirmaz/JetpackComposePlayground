package com.example.jetpackcomposeplayground.samples.marsphotos.data

import com.example.jetpackcomposeplayground.samples.marsphotos.model.MarsPhoto
import com.example.jetpackcomposeplayground.samples.marsphotos.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}


class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}