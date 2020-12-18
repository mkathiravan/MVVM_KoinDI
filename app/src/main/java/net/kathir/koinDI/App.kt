package net.kathir.koinDI

import android.app.Application
import net.kathir.koinDI.di.module.appModule
import net.kathir.koinDI.di.module.repoModule
import net.kathir.koinDI.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}