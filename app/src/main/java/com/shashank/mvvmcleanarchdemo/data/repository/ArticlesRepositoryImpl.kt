package com.shashank.mvvmcleanarchdemo.data.repository

import com.shashank.mvvmcleanarchdemo.core.common.Resource
import com.shashank.mvvmcleanarchdemo.core.utils.Constants
import com.shashank.mvvmcleanarchdemo.data.mapper.toDomainAllArticles
import com.shashank.mvvmcleanarchdemo.data.service.ArticlesAPI
import com.shashank.mvvmcleanarchdemo.domain.model.AllArticles
import com.shashank.mvvmcleanarchdemo.domain.repository.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesAPI: ArticlesAPI
) : ArticlesRepository {
    override fun getAllArticles(
        filter: String
    ): Flow<Resource<AllArticles>> = flow {
        emit(Resource.Loading())
        val result = articlesAPI.getAllArticles(filter, Constants.API_KEY).toDomainAllArticles()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO).catch {
        emit(Resource.Error(it.message.toString()))
    }
}