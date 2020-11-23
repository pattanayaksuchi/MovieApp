package com.example.tmdbmovie.di

import com.example.tmdbmovie.BuildConfig
import com.example.tmdbmovie.data.netwok.AddKeyInterceptor
import com.example.tmdbmovie.data.netwok.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val baseURL = "https://api.themoviedb.org/"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(AddKeyInterceptor(BuildConfig.API_KEY)).build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    @Provides
    fun provideTMDBService(retrofit: Retrofit): TMDBService = retrofit.create(TMDBService::class.java)

}