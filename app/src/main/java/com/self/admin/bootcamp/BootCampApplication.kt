package com.self.admin.bootcamp

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BootCampApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BootCampApplication)
            modules(listOf(module))
        }
        Stetho.initializeWithDefaults(this)
    }
}