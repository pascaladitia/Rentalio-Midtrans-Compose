package com.pascal.rentalio.di

import com.pascal.rentalio.ui.screen.history.HistoryViewModel
import com.pascal.rentalio.ui.screen.home.HomeViewModel
import com.pascal.rentalio.ui.screen.payment.PaymentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { HistoryViewModel(get(), get()) }
    viewModel { PaymentViewModel(get()) }
}

