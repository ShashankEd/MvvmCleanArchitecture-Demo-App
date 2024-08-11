package com.shashank.mvvmcleanarchdemo.presentation.state

import com.shashank.mvvmcleanarchdemo.domain.model.AllArticles

data class AllArticlesViewModelState(
    val articles: AllArticles? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean = false
)
