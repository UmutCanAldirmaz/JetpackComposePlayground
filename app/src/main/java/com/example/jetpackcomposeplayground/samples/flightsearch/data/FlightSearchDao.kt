package com.example.jetpackcomposeplayground.samples.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Flight Search veritabanı işlemlerini yöneten Room Data Access Object (DAO).
 */
@Dao
interface FlightSearchDao {

    /**
     * Kullanıcı arama metni ile havalimanı kodunda (iata_code) veya adında (name) eşleşen havalimanlarını arar.
     * Yolcu sayısına göre azalan sırayla (passengers DESC) sıralar.
     */
    @Query("SELECT * FROM airport WHERE iata_code LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%' ORDER BY passengers DESC")
    fun getAirportsBySearch(query: String): Flow<List<Airport>>

    /**
     * Belirtilen IATA koduna sahip havalimanını getirir.
     */
    @Query("SELECT * FROM airport WHERE iata_code = :code LIMIT 1")
    suspend fun getAirportByCode(code: String): Airport?

    /**
     * Veritabanındaki tüm havalimanlarını getirir.
     */
    @Query("SELECT * FROM airport ORDER BY passengers DESC")
    fun getAllAirports(): Flow<List<Airport>>

    /**
     * Kalkış havalimanı haricindeki tüm varış havalimanlarını yolcu sayısına göre azalan sırada getirir.
     */
    @Query("SELECT * FROM airport WHERE iata_code != :departureCode ORDER BY passengers DESC")
    fun getAllAirportsExcept(departureCode: String): Flow<List<Airport>>

    /**
     * Tüm favori rotaları id sırasıyla getirir.
     */
    @Query("SELECT * FROM favorite ORDER BY id ASC")
    fun getAllFavorites(): Flow<List<Favorite>>

    /**
     * Belirli bir kalkış ve varış rotasının favorilerde olup olmadığını kontrol etmek için tekil favori getirir.
     */
    @Query("SELECT * FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode LIMIT 1")
    suspend fun getFavorite(departureCode: String, destinationCode: String): Favorite?

    /**
     * Yeni bir favori rota ekler.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    /**
     * Belirtilen kalkış ve varış rotasını favorilerden siler.
     */
    @Query("DELETE FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode")
    suspend fun deleteFavorite(departureCode: String, destinationCode: String)
}
