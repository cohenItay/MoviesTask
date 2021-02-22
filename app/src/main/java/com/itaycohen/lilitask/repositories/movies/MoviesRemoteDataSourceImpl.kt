package com.itaycohen.lilitask.repositories.movies

import com.itaycohen.lilitask.models.Movie
import com.itaycohen.lilitask.network.MoviesWebService
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesWebService: MoviesWebService
) : MoviesRemoteDataSource {

    override suspend fun fetchMovies(): List<Movie> =
        moviesWebService.fetchMovies().moviesList ?: listOf()
}