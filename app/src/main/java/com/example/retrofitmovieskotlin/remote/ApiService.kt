package com.example.retrofitmovieskotlin.remote

import com.example.retrofitmovieskotlin.constants.StaticValues
import com.example.retrofitmovieskotlin.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(StaticValues.MOVIES_API)
    fun movies(): Call<List<Movie>>

}