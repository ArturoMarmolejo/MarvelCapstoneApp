package com.arturomarmolejo.marvelcapstoneapp.model.events


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventResult(
    @Json(name = "characters")
    val characters: Characters = Characters(),
    @Json(name = "comics")
    val comics: Comics = Comics(),
    @Json(name = "creators")
    val creators: Creators = Creators(),
    @Json(name = "description")
    val description: String = "",
    @Json(name = "end")
    val end: String? = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "next")
    val next: Next? = Next(),
    @Json(name = "previous")
    val previous: Previous? = Previous(),
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "series")
    val series: Series = Series(),
    @Json(name = "start")
    val start: String? = "",
    @Json(name = "stories")
    val stories: Stories = Stories(),
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "title")
    val title: String = "",
    @Json(name = "urls")
    val urls: List<Url> = listOf()
)