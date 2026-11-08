package com.example.jetpackcomposeplayground.samples.sqlbasics.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmailDao {
    @Query("SELECT * FROM email")
    fun getAll(): List<Email>
}
