package com.clem.androidarch.data.repository

import com.clem.androidarch.data.local.LocalDataSource
import com.clem.androidarch.data.model.ArticleData
import com.clem.androidarch.data.remote.RemoteDataSource

class DataRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getDataRepository(page: String): ArticleData =
        remoteDataSource.fetchRepositories(page)

    fun cachePageNumber(page: String) =
        localDataSource.cachePageNumber(page)
}