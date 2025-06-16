package com.example.khabbar.data.remote

import com.example.khabbar.data.remote.dto.NewsResponce
import com.example.khabbar.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
//        @Query("q") q: String = "bitcoin",
        @Query("apiKey") apiKey: String = Constants.API_KEY

    ): NewsResponce

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("pageSize") pageSize: Int = 50, // Get more articles in single request
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ) : NewsResponce

}