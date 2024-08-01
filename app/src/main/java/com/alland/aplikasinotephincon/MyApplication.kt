package com.alland.aplikasinotephincon

import android.app.Application
import com.alland.aplikasinotephincon.di.AppComponent
import com.alland.aplikasinotephincon.di.DaggerAppComponent

class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}