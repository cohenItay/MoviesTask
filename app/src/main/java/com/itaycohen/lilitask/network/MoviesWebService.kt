package com.itaycohen.lilitask.network

import com.itaycohen.lilitask.models.Movies
import retrofit2.http.GET

interface MoviesWebService {
    @GET("/yossiavramov/MoviesList/master/MoviesList")
    suspend fun fetchMovies(): Movies
}
