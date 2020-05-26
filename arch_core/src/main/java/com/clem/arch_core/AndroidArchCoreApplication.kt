package com.clem.arch_core

import androidx.multidex.MultiDexApplication
import com.tencent.mmkv.MMKV

abstract class AndroidArchCoreApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        MMKV.initialize(this)
        initKoin()
    }

    abstract fun initKoin()

    companion object {
        lateinit var instance: AndroidArchCoreApplication
    }

}