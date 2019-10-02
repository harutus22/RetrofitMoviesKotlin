package com.example.retrofitmovieskotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.retrofitmovieskotlin.repository.MovieRepository

class MovieViewModel: ViewModel() {
    private val movieRepository = MovieRepository.getInstance()
    val movieLiveData = movieRepository.movies
}