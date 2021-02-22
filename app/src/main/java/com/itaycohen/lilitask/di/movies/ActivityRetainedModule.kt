package com.itaycohen.lilitask.di.movies

import com.itaycohen.lilitask.repositories.movies.MoviesRemoteDataSource
import com.itaycohen.lilitask.repositories.movies.MoviesRemoteDataSourceImpl
import com.itaycohen.lilitask.repositories.movies.MoviesRepository
import com.itaycohen.lilitask.repositories.movies.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Specifies **which** implementation to bind for an interface.
 */
@Module
@InstallIn(SingletonComponent::class)
interface MoviesBoundedInstances {

    @Binds
    fun bindMoviesRemoteDataSource(
        moviesDataSource: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @Binds
    fun bindMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ) : MoviesRepository
}