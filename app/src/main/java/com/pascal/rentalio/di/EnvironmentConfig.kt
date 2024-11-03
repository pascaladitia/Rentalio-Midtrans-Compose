package com.pascal.rentalio.di

import com.pascal.rentalio.BuildConfig

object EnvironmentConfig {
    const val BASE_DOMAIN = BuildConfig.BASE_URL
    const val BASE_URL = "https://$BASE_DOMAIN/"
    val allowedSSlFingerprints = emptyList<String>()
}