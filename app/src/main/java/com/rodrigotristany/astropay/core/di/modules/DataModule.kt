package com.rodrigotristany.astropay.core.di.modules

import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.rodrigotristany.astropay.data.api.WeatherApi
import com.rodrigotristany.astropay.data.api.WeatherService
import com.rodrigotristany.astropay.data.mapper.ApiErrorMapper
import com.rodrigotristany.astropay.domain.models.ErrorHandler
import com.rodrigotristany.astropay.domain.repositories.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule{
    companion object {
        private const val BASE_URL: String = "https://api.openweathermap.org/"
    }

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor { chain ->
                    var request : Request = chain.request()
                    val url : HttpUrl = request .url().newBuilder().build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient) : Retrofit =
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    @Provides
    @Singleton
    fun provideWeatherApi() : WeatherApi {
        return provideRetrofit(BASE_URL, provideClient()).create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherService() : WeatherRepository {
        return WeatherService(provideWeatherApi())
    }

    @Provides
    @Singleton
    fun provideApiErrorMapper() : ErrorHandler {
        return ApiErrorMapper()
    }
}