package com.example.jetpackcomposeplayground.samples.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * `Schedule` tablosu üzerinde okuma sorgularını yürüten DAO arayüzü.
 */
@Dao
interface BusScheduleDao {
    /**
     * Tüm otobüs tarifesini varış zamanına göre artan sırada getirir.
     */
    @Query(
        """
        SELECT * FROM schedule 
        ORDER BY arrival_time ASC    
        """
    )
    fun getAll(): Flow<List<BusSchedule>>

    /**
     * Belirtilen durak adına göre otobüs tarifesini varış zamanına göre artan sırada getirir.
     */
    @Query(
        """
        SELECT * FROM schedule 
        WHERE stop_name = :stopName 
        ORDER BY arrival_time ASC 
        """
    )
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}
