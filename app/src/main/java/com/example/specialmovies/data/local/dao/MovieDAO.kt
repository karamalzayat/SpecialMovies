package com.example.specialmovies.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.specialmovies.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: MovieEntity)

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity)

    @Query("SELECT * FROM favorites")
     fun getAllFavorites(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: Int): MovieEntity?
}