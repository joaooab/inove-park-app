package br.com.inove_park_app.di

import br.com.inove_park_app.ui.home.BottomSheetParkViewModel
import br.com.inove_park_app.ui.home.HomeViewModel
import br.com.inove_park_app.ui.map.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BottomSheetParkViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { MapViewModel() }
}