package com.example.jetpackcomposeplayground

import android.app.Application
import com.example.jetpackcomposeplayground.samples.amphibians.data.AppContainer as AmphibiansAppContainer
import com.example.jetpackcomposeplayground.samples.amphibians.data.DefaultAppContainer as DefaultAmphibiansAppContainer
import com.example.jetpackcomposeplayground.samples.busschedule.data.BusScheduleDatabase
import com.example.jetpackcomposeplayground.samples.inventory.data.AppContainer as InventoryAppContainer
import com.example.jetpackcomposeplayground.samples.inventory.data.AppDataContainer as DefaultInventoryAppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.AppContainer as MarsAppContainer
import com.example.jetpackcomposeplayground.samples.marsphotos.data.DefaultAppContainer as DefaultMarsAppContainer

class PlaygroundApplication : Application() {
    lateinit var marsPhotosContainer: MarsAppContainer
    lateinit var amphibiansContainer: AmphibiansAppContainer
    lateinit var inventoryContainer: InventoryAppContainer
    val busScheduleDatabase: BusScheduleDatabase by lazy { BusScheduleDatabase.getDatabase(this) }

    val container: InventoryAppContainer get() = inventoryContainer

    override fun onCreate() {
        super.onCreate()
        marsPhotosContainer = DefaultMarsAppContainer()
        amphibiansContainer = DefaultAmphibiansAppContainer()
        inventoryContainer = DefaultInventoryAppContainer(this)
    }
}
