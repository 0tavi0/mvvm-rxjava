package com.example.mvvmrxjava

import android.app.Application
import com.example.mvvmrxjava.di.AppModules.modules
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this@App, listOf(modules))
    }
}