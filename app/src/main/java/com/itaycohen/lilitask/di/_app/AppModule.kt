package com.itaycohen.lilitask.di._app

import com.google.gson.Gson
import com.itaycohen.lilitask.BuildConfig
import com.itaycohen.lilitask.network.MoviesWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Specifies **which** implementation to bind for an interface.
 */
@Module
@InstallIn(SingletonComponent::class)
interface AppBoundedInstances {

}

/**
 * Specifies **how** to bind injected dependencies to the [SingletonComponent] for global application scoped objects.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()


    @Provides
    fun provideMoviesWebService(@GlobalOkHttpClient okHttpClient: OkHttpClient) : MoviesWebService {
        return createWebService(okHttpClient, BuildConfig.SERVER_BASE_URL)
    }
}