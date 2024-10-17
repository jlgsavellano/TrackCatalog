package com.appetiser.trackcatalog.di

import com.appetiser.trackcatalog.BuildConfig
import com.appetiser.trackcatalog.data.api.AppleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides network related interaction on application
 * Using DaggerHilt for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAppleApiService(retrofit: Retrofit): AppleApiService =
        retrofit.create(AppleApiService::class.java)


}