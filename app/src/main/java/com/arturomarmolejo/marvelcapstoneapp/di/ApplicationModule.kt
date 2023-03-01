package com.arturomarmolejo.marvelcapstoneapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
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

//    @Provides
//    @Singleton
//    fun provideBusinessesDAO(
//        @ApplicationContext context: Context
//    ): RestaurantsDAO =
//        Room.databaseBuilder(
//            context,
//            RestaurantsDatabase::class.java,
//            "restaurants-db"
//        ).build().getRestaurantsDAO()

}