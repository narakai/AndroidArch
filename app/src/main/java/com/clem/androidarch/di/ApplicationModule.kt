package com.clem.androidarch.di

import com.clem.arch_core.utils.CONNECT_TIMEOUT
import com.clem.arch_core.utils.HOST
import com.clem.arch_core.utils.READ_TIMEOUT
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(String.format("%1\$s://%2\$s/", SCHEMA_HTTPS, HOST))
            .build()
    }
}

val applicationModules = listOf(
    networkModule
)

private const val SCHEMA_HTTPS = "https"