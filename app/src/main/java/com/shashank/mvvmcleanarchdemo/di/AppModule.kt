package com.shashank.mvvmcleanarchdemo.di

import com.shashank.mvvmcleanarchdemo.core.utils.Constants
import com.shashank.mvvmcleanarchdemo.data.repository.ArticlesRepositoryImpl
import com.shashank.mvvmcleanarchdemo.data.service.ArticlesAPI
import com.shashank.mvvmcleanarchdemo.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun articlesAPI(retrofit: Retrofit) : ArticlesAPI = retrofit.create(ArticlesAPI::class.java)

    @Provides
    @Singleton
    fun provideArticlesRepository(api: ArticlesAPI) : ArticlesRepository {
        return ArticlesRepositoryImpl(api)
    }
}