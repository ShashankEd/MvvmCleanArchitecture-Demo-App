package com.shashank.mvvmcleanarchdemo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.mvvmcleanarchdemo.core.common.Resource
import com.shashank.mvvmcleanarchdemo.domain.usecase.ArticleUseCase
import com.shashank.mvvmcleanarchdemo.presentation.state.AllArticlesViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val articleUseCase: ArticleUseCase) : ViewModel() {
    private val _articlesState = MutableStateFlow(AllArticlesViewModelState())
    val articlesState: StateFlow<AllArticlesViewModelState>
        get() = _articlesState

    fun getAllArticles(filter: String) {
        articleUseCase.getAllArticles(filter).onEach {
            when(it) {
                is Resource.Error -> {
                    _articlesState.value = AllArticlesViewModelState().copy(errorMessage = it.status)
                }
                is Resource.Loading -> {
                    _articlesState.value = AllArticlesViewModelState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _articlesState.value = AllArticlesViewModelState().copy(articles = it.model)
                }
            }
        }.launchIn(viewModelScope)

    }
}