package com.appetiser.trackcatalog.di.module

import android.content.Context
import androidx.room.Room
import com.appetiser.trackcatalog.data.local.dao.TrackDao
import com.appetiser.trackcatalog.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides database related interaction on application
 * Using DaggerHilt for dependency injection
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "track_database").build()

    @Provides
    fun provideTrackDao(database: AppDatabase): TrackDao =
        database.trackDao()
}