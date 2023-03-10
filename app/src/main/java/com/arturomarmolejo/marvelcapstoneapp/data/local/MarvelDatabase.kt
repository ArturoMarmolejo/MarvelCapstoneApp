package com.arturomarmolejo.marvelcapstoneapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.*

@Database(
    entities = [
        CharacterEntity::class,
    ],
    version = 1
)

@TypeConverters(
    ComicsTypeConverter::class,
    EventsTypeConverter::class,
    SeriesTypeConverter::class,
    StoriesTypeConverter::class,
    ThumbnailTypeConverter::class,
    UrlTypeConverter::class,
    UrlListTypeConverter::class
)

abstract class MarvelDatabase: RoomDatabase() {
    abstract val marvelDao : MarvelDAO
}