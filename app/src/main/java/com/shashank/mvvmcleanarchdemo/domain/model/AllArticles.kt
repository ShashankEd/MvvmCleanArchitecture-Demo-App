package com.shashank.mvvmcleanarchdemo.domain.model


data class AllArticles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
