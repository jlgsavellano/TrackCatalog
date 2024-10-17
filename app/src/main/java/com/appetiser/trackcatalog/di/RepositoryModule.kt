package com.appetiser.trackcatalog.di

import com.appetiser.trackcatalog.data.api.AppleApiService
import com.appetiser.trackcatalog.data.db.TrackDao
import com.appetiser.trackcatalog.data.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides clean API for accessing data on application
 * Using DaggerHilt for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTrackRepository(apiService: AppleApiService, dao: TrackDao): TrackRepository =
        TrackRepository(apiService, dao)
}