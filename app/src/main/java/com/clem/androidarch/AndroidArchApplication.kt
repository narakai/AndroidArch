package com.clem.androidarch

import androidx.multidex.MultiDexApplication
import com.clem.arch_core.AndroidArchCoreApplication
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
//            modules(applicationModules)
        }
    }

}