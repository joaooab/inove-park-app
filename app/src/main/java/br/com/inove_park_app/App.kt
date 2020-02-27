package br.com.inove_park_app

import android.app.Application
import br.com.inove_park_app.di.apiModule
import br.com.inove_park_app.di.repositoryModule
import br.com.inove_park_app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(apiModule, viewModelModule, repositoryModule))
        }
    }
}