package com.example.specialmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.specialmovies.data.local.dao.MovieDao
import com.example.specialmovies.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}