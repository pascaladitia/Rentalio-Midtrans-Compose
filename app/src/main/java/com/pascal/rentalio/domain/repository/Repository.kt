package com.pascal.rentalio.domain.repository

import com.pascal.rentalio.data.remote.AppService
import com.pascal.rentalio.domain.model.ResponseHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

internal class Repository(
    private val appService: AppService
) : IRepository {

    override suspend fun history(): Flow<Response<ResponseHistory?>> {
        return flowOf(appService.history())
    }
}