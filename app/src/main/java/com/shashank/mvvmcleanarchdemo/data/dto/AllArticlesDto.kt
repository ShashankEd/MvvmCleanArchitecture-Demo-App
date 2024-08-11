package com.shashank.mvvmcleanarchdemo.data.dto

data class AllArticlesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)