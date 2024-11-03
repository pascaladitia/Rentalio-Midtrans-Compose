package com.pascal.rentalio.data.setup

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import java.util.concurrent.TimeUnit

class HttpClientFactory(context: Context, private val baseDomain: String, private val certificatesPins: List<String>) {
    val abstractClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        if (certificatesPins.isNotEmpty()) builder.certificatePinner(createCertificatePinner())

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(ChuckerInterceptor.Builder(context).build())
            /*
            builder.addNetworkInterceptor(NetworkModule.createHttpLoggingInterceptor())
            */
        }

        builder.build()
    }

    private fun createCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder().apply {
            certificatesPins.forEach { this.add(baseDomain, it) }
        }.build()
    }

}