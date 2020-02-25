package com.example.xpenses

import android.app.Application
import com.example.xpenses.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class XpensesApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@XpensesApplication)
            modules(appModule)
        }
    }
}