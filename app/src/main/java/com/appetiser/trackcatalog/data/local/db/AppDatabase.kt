package com.appetiser.trackcatalog.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appetiser.trackcatalog.data.local.entity.Track
import com.appetiser.trackcatalog.data.local.dao.TrackDao

/**
 * Declaration of Database Models and Data Access Objects
 */
@Database(entities = [Track::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trackDao(): TrackDao
}