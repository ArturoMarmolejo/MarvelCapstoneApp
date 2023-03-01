package com.arturomarmolejo.marvelcapstoneapp.model.character


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "comics")
    val comics: Comics = Comics(),
    @Json(name = "description")
    val description: String = "",
    @Json(name = "events")
    val events: Events = Events(),
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "series")
    val series: Series = Series(),
    @Json(name = "stories")
    val stories: Stories = Stories(),
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "urls")
    val urls: List<Url> = listOf()
)