package com.arturomarmolejo.marvelcapstoneapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CreatorEntity
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.arturomarmolejo.marvelcapstoneapp.model.creator.*
import com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators.*

@TypeConverters(
    ComicsTypeConverter::class,
    EventsTypeConverter::class,
    SeriesTypeConverter::class,
    StoriesTypeConverter::class,
    ThumbnailTypeConverter::class,
    UrlTypeConverter::class,
    UrlListTypeConverter::class
)

data class CreatorModel(
    val id: Int,
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
    val urls: List<Url>, //*

    val isFavorite: Boolean = false
)

fun List<CreatorEntity>.mapFromEntityToCreator(): List<CreatorModel> {
    return this.map {
        CreatorModel(
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