package com.pascal.rentalio.domain.repository.local

import com.pascal.rentalio.data.local.LocalDataSource
import com.pascal.rentalio.domain.model.Booking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class LocalRepository(
    private val localDataSource: LocalDataSource
): ILocalRepository {
    override suspend fun getHistory(): Flow<List<Booking>> {
        return flowOf(localDataSource.getHistory())
    }

    override suspend fun addHistory(item: Booking): Flow<Unit> {
        return flowOf(localDataSource.insertHistoryItem(item))
    }

    override suspend fun deleteHistory(item: Booking): Flow<Unit> {
        return flowOf(localDataSource.deleteHistoryItem(item))
    }
}