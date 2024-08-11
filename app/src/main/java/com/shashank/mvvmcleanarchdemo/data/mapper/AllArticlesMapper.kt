package com.shashank.mvvmcleanarchdemo.data.mapper

import com.shashank.mvvmcleanarchdemo.data.dto.AllArticlesDto
import com.shashank.mvvmcleanarchdemo.domain.model.AllArticles
import com.shashank.mvvmcleanarchdemo.domain.model.Article

fun AllArticlesDto.toDomainAllArticles(): AllArticles =
    AllArticles(articles.map {
        Article(it.author, it.title, it.urlToImage, it.publishedAt)
    }, status, totalResults)
