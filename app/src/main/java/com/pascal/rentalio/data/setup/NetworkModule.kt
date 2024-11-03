package com.pascal.rentalio.data.setup

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object NetworkModule {

    fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun basicHeaderInterceptor() = Interceptor {
        val request = it.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        it.proceed(request)
    }
}