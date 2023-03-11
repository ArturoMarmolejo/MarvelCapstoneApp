package com.arturomarmolejo.marvelcapstoneapp.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
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

fun List<CharacterResult>?.mapToCharacterEntity(): List<CharacterEntity> {
    return this?.map {
        CharacterEntity(
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
    } ?: emptyList()
}


