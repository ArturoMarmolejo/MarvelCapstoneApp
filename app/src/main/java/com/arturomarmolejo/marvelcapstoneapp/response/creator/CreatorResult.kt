package com.arturomarmolejo.marvelcapstoneapp.response.creator


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatorResult(
    @Json(name = "comics")
    val comics: Comics = Comics(),
    @Json(name = "events")
    val events: Events = Events(),
    @Json(name = "firstName")
    val firstName: String = "",
    @Json(name = "fullName")
    val fullName: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "lastName")
    val lastName: String = "",
    @Json(name = "middleName")
    val middleName: String = "",
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "series")
    val series: Series = Series(),
    @Json(name = "stories")
    val stories: Stories = Stories(),
    @Json(name = "suffix")
    val suffix: String = "",
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "urls")
    val urls: List<Url> = listOf()
)