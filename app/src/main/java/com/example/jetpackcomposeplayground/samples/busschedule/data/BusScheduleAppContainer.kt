package com.example.jetpackcomposeplayground.samples.busschedule.data

import android.content.Context

/**
 * Bus Schedule uygulaması için Manuel Bağımlılık Enjeksiyonu (Manual DI) Container Arayüzü.
 */
interface BusScheduleAppContainer {
    val busScheduleDatabase: BusScheduleDatabase
}

/**
 * Gerçek (Production) bağımlılıkları ilklendiren varsayılan DI Container sınıfı.
 */
class DefaultBusScheduleAppContainer(private val context: Context) : BusScheduleAppContainer {
    override val busScheduleDatabase: BusScheduleDatabase by lazy {
        BusScheduleDatabase.getDatabase(context)
    }
}
