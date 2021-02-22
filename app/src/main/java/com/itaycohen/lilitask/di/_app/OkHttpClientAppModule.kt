package com.itaycohen.lilitask.di._app

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClientAppModule {

    private const val TIMEOUT = 10L

    @Provides
    @GlobalOkHttpClient
    fun provideGlobalOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}

/**
 * creates the desired WebService, using retrofit.
 * A global function which can be referenced from all different hilt modules.
 */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    url: String,
    converterFactory: Converter.Factory = GsonConverterFactory.create()
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()
    return retrofit.create(T::class.java)
}