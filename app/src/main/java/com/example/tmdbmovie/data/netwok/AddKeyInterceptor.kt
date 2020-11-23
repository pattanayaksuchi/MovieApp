package com.example.tmdbmovie.data.netwok

import okhttp3.Interceptor
import okhttp3.Response

class AddKeyInterceptor(private val apiKey : String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()
        val uri = currentRequest.url.newBuilder().addQueryParameter("api_key", apiKey).build()
        val newRequest = currentRequest.newBuilder().url(uri)

        return chain.proceed(newRequest.build())
    }
}