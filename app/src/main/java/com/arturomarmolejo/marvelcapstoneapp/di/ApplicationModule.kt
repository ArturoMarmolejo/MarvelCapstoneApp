package com.arturomarmolejo.marvelcapstoneapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.arturomarmolejo.marvelcapstoneapp.data.local.LocalRepository
import com.arturomarmolejo.marvelcapstoneapp.data.local.LocalRepositoryImpl
import com.arturomarmolejo.marvelcapstoneapp.data.local.MarvelDAO
import com.arturomarmolejo.marvelcapstoneapp.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun providesMarvelDao(
        @ApplicationContext context: Context
    ) : MarvelDAO = Room.databaseBuilder(
        context,
        MarvelDatabase::class.java,
        "marvel-db"
    ).build().marvelDao

    @Provides
    fun providesLocalRepository(
        marvelDAO: MarvelDAO
    ): LocalRepository = LocalRepositoryImpl(marvelDAO)

}