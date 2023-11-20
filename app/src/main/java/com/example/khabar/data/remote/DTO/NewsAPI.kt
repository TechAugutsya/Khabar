package com.example.khabar.data.remote

import com.example.khabar.data.remote.DTO.NewsResponse
import com.example.khabar.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey:String = API_KEY
    ):NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page:Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey:String = API_KEY
    ):NewsResponse

}

