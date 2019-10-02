package com.example.retrofitmovieskotlin.remote

import com.example.retrofitmovieskotlin.constants.StaticValues
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        private var retrofit: Retrofit? = null
        private var apiManager: ApiManager? = null

        @Synchronized
        fun getInstance(): ApiManager{
            return apiManager ?: ApiManager()
        }

        init {
            retrofit = Retrofit.Builder().baseUrl(StaticValues.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

    fun getMovies() = retrofit?.create(ApiService::class.java)
}