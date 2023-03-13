package com.arturomarmolejo.marvelcapstoneapp.data.model

import androidx.room.TypeConverters
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.model.character.*
import com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters.*

@TypeConverters(
    ComicsTypeConverter::class,
    EventsTypeConverter::class,
    SeriesTypeConverter::class,
    StoriesTypeConverter::class,
    ThumbnailTypeConverter::class,
    UrlTypeConverter::class,
    UrlListTypeConverter::class
)
data class CharacterModel(
    val id: Int,
    val comics: Comics, //*
    val description: String,
    val events: Events?, //*
    val name: String,
    val resourceURI: String,
    val series: Series, //*
    val stories: Stories, //*
    val thumbnail: Thumbnail, //*
    val urls: List<Url>, //*

    val isFavorite: Boolean = false
)

fun List<CharacterEntity>.mapFromEntityToCharacter(): List<CharacterModel> =
    this.map {
        CharacterModel(
            id = it.id,
            comics = it.comics,
            description = it.description,
            events = it.events,
            name = it.name,
            resourceURI = it.resourceURI,
            series = it.series,
            stories = it.stories,
            thumbnail = it.thumbnail,
            urls = it.urls.map { url ->
                Url(
                    type = url.type,
                    url = url.url
                )
            },
        )
    }