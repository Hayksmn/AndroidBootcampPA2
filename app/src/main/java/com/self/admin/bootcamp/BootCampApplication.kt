package com.self.admin.bootcamp

import android.app.Application
import com.facebook.stetho.Stetho

class BootCampApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        GlobalInstances.initApiClient()
        GlobalInstances.initImageLoader(this)
    }
}