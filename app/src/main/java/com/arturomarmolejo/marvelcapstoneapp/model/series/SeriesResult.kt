package com.arturomarmolejo.marvelcapstoneapp.model.series


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesResult(
    @Json(name = "characters")
    val characters: Characters = Characters(),
    @Json(name = "comics")
    val comics: Comics = Comics(),
    @Json(name = "creators")
    val creators: Creators = Creators(),
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "endYear")
    val endYear: Int = 0,
    @Json(name = "events")
    val events: Events = Events(),
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "next")
    val next: Next? = Next(),
    @Json(name = "previous")
    val previous: Any? = Any(),
    @Json(name = "rating")
    val rating: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "startYear")
    val startYear: Int = 0,
    @Json(name = "stories")
    val stories: Stories = Stories(),
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "title")
    val title: String = "",
    @Json(name = "type")
    val type: String = "",
    @Json(name = "urls")
    val urls: List<Url> = listOf()
)