package com.pascal.rentalio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pascal.rentalio.data.local.dao.HistoryDao
import com.pascal.rentalio.domain.model.Booking
import com.pascal.rentalio.domain.model.ResponseHistory

@Database(
    entities = [Booking::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
