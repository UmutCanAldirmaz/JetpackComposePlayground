package com.example.jetpackcomposeplayground.samples.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * `assets/database/flight_search.db` dosyasından hazır verileri yükleyen Room veritabanı sınıfı.
 */
@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightSearchDatabase : RoomDatabase() {

    abstract fun flightSearchDao(): FlightSearchDao

    companion object {
        @Volatile
        private var INSTANCE: FlightSearchDatabase? = null

        fun getDatabase(context: Context): FlightSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FlightSearchDatabase::class.java,
                    "flight_search_db"
                )
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
