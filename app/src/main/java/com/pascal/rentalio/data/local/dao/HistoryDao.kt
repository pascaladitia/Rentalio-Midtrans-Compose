package com.pascal.rentalio.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.rentalio.data.local.database.DatabaseConstants
import com.pascal.rentalio.domain.model.Booking

@Dao
abstract class HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertHistory(cachedTest: Booking) : Long

    @Delete
    abstract suspend fun deleteHistory(item: Booking) : Int

    @Query("SELECT count(*) FROM " + DatabaseConstants.TABLE_HISTORY)
    abstract suspend fun countTest(): Int

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_HISTORY}")
    abstract suspend fun getHistoryList(): List<Booking>

    @Query("DELETE FROM ${DatabaseConstants.TABLE_HISTORY}")
    abstract suspend fun clearHistorysTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllHistory(cachedTests: List<Booking>) : List<Long>

}