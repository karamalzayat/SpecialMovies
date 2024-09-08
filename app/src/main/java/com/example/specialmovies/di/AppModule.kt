package com.example.specialmovies.di

import android.content.Context
import androidx.room.Room
import com.example.specialmovies.data.local.AppDatabase
import com.example.specialmovies.data.local.dao.MovieDao
import com.example.specialmovies.data.remote.retrofit.RetrofitClient
import com.example.specialmovies.data.remote.retrofit.WebServices
import com.example.specialmovies.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): WebServices {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        apiService: WebServices,
        movieDao: MovieDao,
        @Named("apiKey") apiKey: String
    ): MovieRepository {
        return MovieRepository(apiService, movieDao, apiKey)
    }
}