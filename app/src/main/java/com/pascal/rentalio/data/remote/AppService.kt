package com.pascal.rentalio.data.remote

import com.pascal.rentalio.domain.model.ResponseHistory
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("b/668b8663ad19ca34f884757e")
    suspend fun history(): Response<ResponseHistory?>
}