package com.itaycohen.lilitask.repositories.movies

import com.itaycohen.lilitask.models.Movie
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun refreshMovies(): List<Movie> {
        // Note: here we can decide from which data source we can retrieve the information
        // Local DB / Remote / Cache / etc..
        // As required - this sample project is only gonna load it remotlry.
        return remoteDataSource.fetchMovies()
    }

}