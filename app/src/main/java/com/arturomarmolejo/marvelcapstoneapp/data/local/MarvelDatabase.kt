package com.arturomarmolejo.marvelcapstoneapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.ComicEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CreatorEntity
import com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters.*


@TypeConverters(
    CharactersTypeConverter::class,
    CollectedIssuesTypeConverter::class,
    CollectedIssuesListTypeConverter::class,
    ComicsTypeConverter::class,
    CreatorTypeConverter::class,
    DateTypeConverter::class,
    DateListTypeConverter::class,
    EventsTypeConverter::class,
    ImageTypeConverter::class,
    ImageListTypeConverter::class,
    ItemTypeConverter::class,
    ItemListTypeConverter::class,
    PriceTypeConverter::class,
    PriceListTypeConverter::class,
    TextObjectTypeConverter::class,
    TextObjectListTypeConverter::class,
    SeriesTypeConverter::class,
    StoriesTypeConverter::class,
    ThumbnailTypeConverter::class,
    UrlTypeConverter::class,
    UrlListTypeConverter::class
)

@Database(
    entities = [
        CharacterEntity::class,
        ComicEntity::class,
        CreatorEntity::class,
    ],
    version = 1,
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ]
)


abstract class MarvelDatabase: RoomDatabase() {
    abstract val marvelDao : MarvelDAO

}