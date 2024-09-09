package com.example.specialmovies.presentation.screens.movieDetails

import androidx.lifecycle.ViewModel
import com.example.specialmovies.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
}