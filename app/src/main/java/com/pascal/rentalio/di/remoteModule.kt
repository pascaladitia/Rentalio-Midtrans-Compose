package com.pascal.rentalio.di

import com.pascal.rentalio.data.setup.AppServiceFactory
import com.pascal.rentalio.data.setup.HttpClientFactory
import com.pascal.rentalio.data.setup.ServiceFactory
import com.pascal.rentalio.di.EnvironmentConfig.BASE_DOMAIN
import com.pascal.rentalio.di.EnvironmentConfig.allowedSSlFingerprints
import com.pascal.rentalio.domain.repository.IRepository
import com.pascal.rentalio.domain.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val remoteModule = module {
    single(named("HTTP_CLIENT")) { HttpClientFactory(androidContext(), BASE_DOMAIN, allowedSSlFingerprints) }
    single(named("SERVICE_FACTORY")) { ServiceFactory(EnvironmentConfig.BASE_URL) }
    single(named("APP_SERVICE")) {
        AppServiceFactory(get(named("HTTP_CLIENT"))).getAppService(get(named("SERVICE_FACTORY")))
    }
    single<IRepository> { Repository(get(named("APP_SERVICE"))) }
}