package com.example.jetpackcomposeplayground.samples.marsphotos

import android.app.Application
import com.example.jetpackcomposeplayground.samples.marsphotos.data.AppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}