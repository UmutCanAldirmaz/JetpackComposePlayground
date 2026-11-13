package com.example.jetpackcomposeplayground

import android.app.Application
import com.example.jetpackcomposeplayground.samples.amphibians.data.AppContainer as AmphibiansAppContainer
import com.example.jetpackcomposeplayground.samples.amphibians.data.DefaultAppContainer as DefaultAmphibiansAppContainer
import com.example.jetpackcomposeplayground.samples.bluromatic.data.AppContainer as BluromaticAppContainer
import com.example.jetpackcomposeplayground.samples.bluromatic.data.DefaultAppContainer as DefaultBluromaticAppContainer
import com.example.jetpackcomposeplayground.samples.busschedule.data.BusScheduleAppContainer
import com.example.jetpackcomposeplayground.samples.busschedule.data.DefaultBusScheduleAppContainer
import com.example.jetpackcomposeplayground.samples.dessertrelease.data.DefaultDessertReleaseAppContainer
import com.example.jetpackcomposeplayground.samples.dessertrelease.data.DessertReleaseAppContainer
import com.example.jetpackcomposeplayground.samples.flightsearch.data.DefaultFlightSearchAppContainer
import com.example.jetpackcomposeplayground.samples.flightsearch.data.FlightSearchAppContainer
import com.example.jetpackcomposeplayground.samples.inventory.data.AppContainer as InventoryAppContainer
import com.example.jetpackcomposeplayground.samples.inventory.data.AppDataContainer as DefaultInventoryAppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.AppContainer as MarsAppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.DefaultAppContainer as DefaultMarsAppContainer

import com.example.jetpackcomposeplayground.samples.waterme.data.AppContainer as WaterMeAppContainer
import com.example.jetpackcomposeplayground.samples.waterme.data.DefaultAppContainer as DefaultWaterMeAppContainer

/**
 * Tüm uygulamanın giriş noktası olan [Application] sınıfı.
 * Manuel Bağımlılık Enjeksiyonu (Manual Dependency Injection) konteynerlarını barındırır.
 */
class PlaygroundApplication : Application() {
    lateinit var marsPhotosContainer: MarsAppContainer
    lateinit var amphibiansContainer: AmphibiansAppContainer
    lateinit var inventoryContainer: InventoryAppContainer
    lateinit var busScheduleContainer: BusScheduleAppContainer
    lateinit var dessertReleaseContainer: DessertReleaseAppContainer
    lateinit var flightSearchContainer: FlightSearchAppContainer
    lateinit var bluromaticContainer: BluromaticAppContainer
    lateinit var waterMeContainer: WaterMeAppContainer

    override fun onCreate() {
        super.onCreate()
        marsPhotosContainer = DefaultMarsAppContainer()
        amphibiansContainer = DefaultAmphibiansAppContainer()
        inventoryContainer = DefaultInventoryAppContainer(this)
        busScheduleContainer = DefaultBusScheduleAppContainer(this)
        dessertReleaseContainer = DefaultDessertReleaseAppContainer(this)
        flightSearchContainer = DefaultFlightSearchAppContainer(this)
        bluromaticContainer = DefaultBluromaticAppContainer(this)
        waterMeContainer = DefaultWaterMeAppContainer(this)
    }
}
