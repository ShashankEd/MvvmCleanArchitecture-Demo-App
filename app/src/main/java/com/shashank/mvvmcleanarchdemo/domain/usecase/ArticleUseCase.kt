package com.shashank.mvvmcleanarchdemo.domain.usecase

import com.shashank.mvvmcleanarchdemo.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticleUseCase @Inject constructor(private val repository: ArticlesRepository) {
    fun getAllArticles(filter: String) = repository.getAllArticles(filter)
}