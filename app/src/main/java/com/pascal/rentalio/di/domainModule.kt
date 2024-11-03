package com.pascal.rentalio.di

import com.pascal.rentalio.domain.usecase.HistoryUC
import com.pascal.rentalio.domain.usecase.LocalUC
import org.koin.dsl.module

val domainModule = module {
    factory { HistoryUC(get()) }
    factory { LocalUC(get()) }
}
