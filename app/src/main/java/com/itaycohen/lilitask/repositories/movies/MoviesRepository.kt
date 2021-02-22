package com.itaycohen.lilitask.repositories.movies

import com.itaycohen.lilitask.models.Movie

interface MoviesRepository {

    /**
     * Refreshes the movie information.
     * @return [List] OF [Movie]
     */
    suspend fun refreshMovies() : List<Movie>
}
