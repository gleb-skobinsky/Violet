package org.violet.violetapp

import android.app.Application
import org.koin.core.context.startKoin
import org.violet.violetapp.di.configureModules

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            configureModules(this@AndroidApp)
        }
    }
}