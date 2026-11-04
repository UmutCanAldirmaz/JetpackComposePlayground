package com.example.jetpackcomposeplayground

import android.app.Application
import com.example.jetpackcomposeplayground.samples.amphibians.data.AppContainer as AmphibiansAppContainer
import com.example.jetpackcomposeplayground.samples.amphibians.data.DefaultAppContainer as DefaultAmphibiansAppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.AppContainer as MarsAppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.DefaultAppContainer as DefaultMarsAppContainer

class PlaygroundApplication : Application() {
    lateinit var marsPhotosContainer: MarsAppContainer
    lateinit var amphibiansContainer: AmphibiansAppContainer

    override fun onCreate() {
        super.onCreate()
        marsPhotosContainer = DefaultMarsAppContainer()
        amphibiansContainer = DefaultAmphibiansAppContainer()
    }
}
