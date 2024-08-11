package com.shashank.mvvmcleanarchdemo.domain.repository

import com.shashank.mvvmcleanarchdemo.core.common.Resource
import com.shashank.mvvmcleanarchdemo.domain.model.AllArticles
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getAllArticles(filter: String): Flow<Resource<AllArticles>>
}