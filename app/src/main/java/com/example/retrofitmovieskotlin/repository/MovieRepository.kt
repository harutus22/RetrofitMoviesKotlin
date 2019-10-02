package com.example.retrofitmovieskotlin.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.retrofitmovieskotlin.constants.StaticValues
import com.example.retrofitmovieskotlin.model.Movie
import com.example.retrofitmovieskotlin.remote.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository private constructor(){
    val movies = MutableLiveData<List<Movie>>()

    companion object{
        private var movieRepository: MovieRepository? = null

        @Synchronized
        fun getInstance(): MovieRepository{
            return movieRepository ?: MovieRepository()
        }
    }
    init {
        val call = ApiManager.getInstance().getMovies()?.movies()
        call?.enqueue(object : Callback<List<Movie>>{
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d(StaticValues.TAG, StaticValues.FAILED_OPERATION + t.localizedMessage)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(!response.isSuccessful) Log.d(StaticValues.TAG, StaticValues.UNSUCCESSFUL_OPERATION)
                else {
                    Log.d(StaticValues.TAG, StaticValues.SUCCESSFUL_OPERATION)
                    movies.value = response.body()
                }
            }
        })
    }
}