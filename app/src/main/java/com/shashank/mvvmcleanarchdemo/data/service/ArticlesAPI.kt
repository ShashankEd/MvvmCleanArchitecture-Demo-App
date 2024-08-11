package com.shashank.mvvmcleanarchdemo.data.service

import com.shashank.mvvmcleanarchdemo.data.dto.AllArticlesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesAPI {
    @GET("everything")
    suspend fun getAllArticles(@Query("q") q: String, @Query("apiKey") apiKey: String): AllArticlesDto
}