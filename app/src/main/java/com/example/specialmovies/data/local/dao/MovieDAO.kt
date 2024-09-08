package com.example.specialmovies.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.specialmovies.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity)

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<MovieEntity>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: Int): MovieEntity?
}