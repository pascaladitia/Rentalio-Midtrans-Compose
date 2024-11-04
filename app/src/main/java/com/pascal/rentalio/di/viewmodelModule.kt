package com.pascal.rentalio.di

import com.pascal.rentalio.ui.screen.detail.DetailViewModel
import com.pascal.rentalio.ui.screen.history.HistoryViewModel
import com.pascal.rentalio.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { HistoryViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
}

