package com.clem.androidarch

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.clem.androidarch.di.applicationModules
import com.clem.arch_core.AndroidArchCoreApplication
import com.clem.arch_core.utils.AppStateTracker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AndroidArchApplication : AndroidArchCoreApplication() {

    override fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AndroidArchApplication)
            androidFileProperties()
            fragmentFactory()
            modules(applicationModules)
        }
    }

    override fun onCreate() {
        super.onCreate()

        // 可追踪应用的是在前台还是后台
        AppStateTracker.track(object : AppStateTracker.AppStateChangeListener {
            override fun appTurnIntoForeground() {
                Log.i("MyApp", "commonLog - appTurnIntoForeground: ")
            }

            override fun appTurnIntoBackground() {
                Log.i("MyApp", "commonLog - appTurnIntoBackground: ")
            }
        })
    }
}