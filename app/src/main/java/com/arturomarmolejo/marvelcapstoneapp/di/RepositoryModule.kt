package com.arturomarmolejo.marvelcapstoneapp.di

import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesMarvelRepositoryImpl(marvelRepositoryImpl: MarvelRepositoryImpl): MarvelRepository
}