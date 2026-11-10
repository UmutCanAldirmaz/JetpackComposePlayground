package com.example.jetpackcomposeplayground.samples.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.jetpackcomposeplayground.PlaygroundApplication
import com.example.jetpackcomposeplayground.samples.busschedule.data.BusSchedule
import com.example.jetpackcomposeplayground.samples.busschedule.data.BusScheduleDao
import kotlinx.coroutines.flow.Flow

/**
 * Otobüs tarifesi verilerini Room DAO üzerinden görünüm katmanına sağlayan ViewModel.
 */
class BusScheduleViewModel(private val busScheduleDao: BusScheduleDao) : ViewModel() {

    /** Tüm otobüs tarifesini getirir. */
    fun getFullSchedule(): Flow<List<BusSchedule>> = busScheduleDao.getAll()

    /** Durak adına özel otobüs tarifesini getirir. */
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> =
        busScheduleDao.getByStopName(stopName)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PlaygroundApplication)
                BusScheduleViewModel(application.busScheduleContainer.busScheduleDatabase.busScheduleDao())
            }
        }
    }
}
