package com.pascal.rentalio.data.local

import com.pascal.rentalio.data.local.database.AppDatabase
import com.pascal.rentalio.domain.model.Booking

class LocalDataSource(private val database: AppDatabase) {

    suspend fun insertHistoryItem(item: Booking) {
        database.historyDao().insertHistory(item)
    }

    suspend fun deleteHistoryItem(item: Booking) {
        database.historyDao().deleteHistory(item)
    }

    suspend fun getHistory(): List<Booking> {
        return database.historyDao().getHistoryList()
    }

}