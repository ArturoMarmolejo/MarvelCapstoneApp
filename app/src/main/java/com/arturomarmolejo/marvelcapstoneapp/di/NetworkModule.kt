package com.arturomarmolejo.marvelcapstoneapp.di

import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelInterceptor
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepositoryImpl
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelServiceApi
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.sql.Time
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun providesMarvelServiceApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): MarvelServiceApi =
        Retrofit.Builder()
            .baseUrl(MarvelServiceApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(MarvelServiceApi::class.java)

    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        marvelInterceptor: MarvelInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(marvelInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    fun providesMarvelInterceptor(): MarvelInterceptor = MarvelInterceptor()

//    @Provides
//    fun providesMarvelRepository(
//        marvelServiceApi: MarvelServiceApi
//    ): MarvelRepository = MarvelRepositoryImpl(marvelServiceApi)


    @Provides
    fun providesOkHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}