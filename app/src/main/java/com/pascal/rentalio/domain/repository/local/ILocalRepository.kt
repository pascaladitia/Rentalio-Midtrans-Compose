package com.pascal.rentalio.domain.repository.local
import com.pascal.rentalio.domain.model.Booking
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    suspend fun getHistory(): Flow<List<Booking>>
    suspend fun addHistory(item: Booking): Flow<Unit>
    suspend fun deleteHistory(item: Booking): Flow<Unit>
}