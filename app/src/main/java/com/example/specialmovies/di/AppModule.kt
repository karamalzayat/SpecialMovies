package com.example.specialmovies.di

import android.content.Context
import androidx.room.Room
import com.example.specialmovies.data.local.AppDatabase
import com.example.specialmovies.data.local.dao.MovieDao
import com.example.specialmovies.data.remote.retrofit.WebServices
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.data.repository.MovieRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): WebServices =
        Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebServices::class.java)


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
//        apiKey: String
    ): MovieRepository {
        return MovieRepositoryImp(apiService, movieDao)
    }
}