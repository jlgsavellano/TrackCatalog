package com.appetiser.trackcatalog.di.module

import com.appetiser.trackcatalog.data.remote.api.ApiService
import com.appetiser.trackcatalog.data.local.dao.TrackDao
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
    fun provideTrackRepository(apiService: ApiService, dao: TrackDao): TrackRepository =
        TrackRepository(apiService, dao)
}