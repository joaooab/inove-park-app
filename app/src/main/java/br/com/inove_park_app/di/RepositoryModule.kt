package br.com.inove_park_app.di

import br.com.inove_park_app.data.maps.MapsRepository
import br.com.inove_park_app.data.maps.MapsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MapsRepository> { MapsRepositoryImpl(get()) }
}
