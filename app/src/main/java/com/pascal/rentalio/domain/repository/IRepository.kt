package com.pascal.rentalio.domain.repository

import com.pascal.rentalio.domain.model.ResponseHistory
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRepository {

    suspend fun history(): Flow<Response<ResponseHistory?>>
}