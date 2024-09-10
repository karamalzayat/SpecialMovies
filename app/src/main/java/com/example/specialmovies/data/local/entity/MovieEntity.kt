package com.example.specialmovies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class MovieEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String?
)