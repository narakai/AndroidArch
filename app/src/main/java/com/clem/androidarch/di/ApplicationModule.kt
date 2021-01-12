package com.clem.androidarch.di

import com.clem.androidarch.data.local.LocalDataSource
import com.clem.androidarch.data.remote.RemoteDataSource
import com.clem.androidarch.data.repository.DataRepository
import com.clem.androidarch.ui.main.activity.GetFoodActivity
import com.clem.androidarch.ui.main.activity.MainActivity
import com.clem.androidarch.ui.main.fragment.SearchFragment
import com.clem.androidarch.ui.main.viewmodel.GetFoodViewModel
import com.clem.androidarch.ui.main.viewmodel.MainViewModel
import com.clem.androidarch.ui.main.viewmodel.SearchViewModel
import com.clem.arch_core.utils.*
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val applicationModule = module {
    single {
        LocalDataSource(MMKV.mmkvWithID(
                MMKV_ID,
                MMKV.SINGLE_PROCESS_MODE,
                MMKV_CRYPT_KEY
        ))
    }

    single { RemoteDataSource(get()) }
}

val repositoryModule = module {
    single { DataRepository(get(), get()) }
}

val mainVMModule = module {
    scope<MainActivity> {
        viewModel { MainViewModel(get()) }
    }
}

val getFoodVMModule = module {
    scope<GetFoodActivity> {
        viewModel { GetFoodViewModel(get()) }
    }
}

val searchVMModule = module {
    scope<SearchFragment> {
        viewModel { SearchViewModel(get()) }
    }
}

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
                .client(get())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HOST)
                .build()
    }
}

val applicationModules = listOf(
        applicationModule,
        repositoryModule,
        mainVMModule,
        getFoodVMModule,
        searchVMModule,
        networkModule
)