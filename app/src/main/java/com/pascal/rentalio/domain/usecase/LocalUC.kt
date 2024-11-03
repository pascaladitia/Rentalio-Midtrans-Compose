package com.pascal.rentalio.domain.usecase

import com.pascal.rentalio.domain.model.Booking
import com.pascal.rentalio.domain.model.ResponseHistory
import com.pascal.rentalio.domain.repository.local.ILocalRepository
import kotlinx.coroutines.flow.Flow

class LocalUC (private val repository: ILocalRepository) {
    suspend fun getHistory(): Flow<List<Booking>> {
        return repository.getHistory()
    }

    suspend fun addHistory(item: Booking): Flow<Unit> {
        return repository.addHistory(item)
    }

    suspend fun deleteHistory(item: Booking): Flow<Unit> {
        return repository.deleteHistory(item)
    }
}