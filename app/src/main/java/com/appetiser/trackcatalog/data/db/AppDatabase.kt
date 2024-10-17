package com.appetiser.trackcatalog.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Declaration of Database Models and Data Access Objects
 */
@Database(entities = [Track::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trackDao(): TrackDao
}