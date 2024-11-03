package com.pascal.rentalio.di

import android.app.Application
import localModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            KoinLogger()
            modules(viewModelModule, localModule, remoteModule, domainModule)
        }
    }
}