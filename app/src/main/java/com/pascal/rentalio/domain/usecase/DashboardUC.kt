package com.pascal.rentalio.domain.usecase

import com.pascal.rentalio.domain.model.ResponseHistory
import com.pascal.rentalio.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class HistoryUC (private val repository: IRepository) {
    suspend fun execute(): Flow<Response<ResponseHistory?>> {
        return repository.history()
    }
}