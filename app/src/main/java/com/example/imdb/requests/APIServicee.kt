package com.example.imdb.requests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServicee {
    companion object {
        val Base_URL = "https://api.themoviedb.org"
        var retrofit: Retrofit? = null
        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    }

}