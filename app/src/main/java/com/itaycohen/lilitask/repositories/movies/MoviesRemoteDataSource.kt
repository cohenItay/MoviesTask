package com.itaycohen.lilitask.repositories.movies

import com.itaycohen.lilitask.models.Movie

interface MoviesRemoteDataSource {

    suspend fun fetchMovies() : List<Movie>
}