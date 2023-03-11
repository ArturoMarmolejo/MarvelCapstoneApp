package com.arturomarmolejo.marvelcapstoneapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arturomarmolejo.marvelcapstoneapp.model.character.*
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Comics
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Events
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Series
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Stories
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Thumbnail
import com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.*


@TypeConverters(
    ComicsTypeConverter::class,
    EventsTypeConverter::class,
    SeriesTypeConverter::class,
    StoriesTypeConverter::class,
    ThumbnailTypeConverter::class,
    UrlTypeConverter::class,
    UrlListTypeConverter::class
)

@Entity(tableName =  "creators")
data class CreatorEntity(
    @PrimaryKey val id: Int,
    val comics: Comics, //*
    val events: Events, //*
    val firstName: String,
    val fullName: String,
    val lastName: String,
    val middleName: String,
    val modified: String,
    val resourceURI: String,
    val series: Series, //*
    val stories: Stories, //*
    val suffix: String,
    val thumbnail: Thumbnail, //*
    val urls: List<Url>?, //*

    val isFavorite: Boolean = false
)

fun List<CreatorResult>?.mapToCreatorEntity(): List<CreatorEntity> {
    return this?.map {
        CreatorEntity(
            id = it.id,
            comics = it.comics,
            events = it.events,
            firstName = it.firstName,
            fullName = it.fullName,
            middleName = it.middleName,
            modified = it.modified,
            lastName = it.lastName,
            resourceURI = it.resourceURI,
            suffix = it.suffix,
            thumbnail = it.thumbnail,
            series = it.series,
            stories = it.stories,
            urls = it.urls.map { url ->
                Url(
                    type = url.type,
                    url = url.url
                )
            },
        )

    } ?: emptyList()
}