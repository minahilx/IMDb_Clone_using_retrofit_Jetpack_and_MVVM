package com.example.imdb.requests

import com.example.imdb.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface MovieINterface {


    @GET("/3/movie/popular?api_key=aa60d07b5ba6b95ebc1e7b1148ffe894")
    fun getMovieList(): Call<Response>

}