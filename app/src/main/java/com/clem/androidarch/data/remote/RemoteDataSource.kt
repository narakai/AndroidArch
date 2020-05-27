package com.clem.androidarch.data.remote

import com.clem.androidarch.data.model.ArticleData
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class RemoteDataSource (
    retrofit: Retrofit
) {
    private val service: Service = retrofit.create(Service::class.java)

    suspend fun fetchRepositories(page: String): ArticleData =
        service.fetchArticles(page)

    interface Service {
        @GET("article/list/{page}/json")
        suspend fun fetchArticles(@Path("page") page: String): ArticleData
    }
}