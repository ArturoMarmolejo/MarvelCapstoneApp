package com.arturomarmolejo.marvelcapstoneapp.model.comic


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsResult(
    @Json(name = "characters")
    val characters: Characters = Characters(),
    @Json(name = "collectedIssues")
    val collectedIssues: List<CollectedIssue> = listOf(),
    @Json(name = "collections")
    val collections: List<Any> = listOf(),
    @Json(name = "creators")
    val creators: Creators = Creators(),
    @Json(name = "dates")
    val dates: List<Date> = listOf(),
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "diamondCode")
    val diamondCode: String = "",
    @Json(name = "digitalId")
    val digitalId: Int = 0,
    @Json(name = "ean")
    val ean: String = "",
    @Json(name = "events")
    val events: Events = Events(),
    @Json(name = "format")
    val format: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "images")
    val images: List<Image> = listOf(),
    @Json(name = "isbn")
    val isbn: String = "",
    @Json(name = "issn")
    val issn: String = "",
    @Json(name = "issueNumber")
    val issueNumber: Int = 0,
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "pageCount")
    val pageCount: Int = 0,
    @Json(name = "prices")
    val prices: List<Price> = listOf(),
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "series")
    val series: Series = Series(),
    @Json(name = "stories")
    val stories: Stories = Stories(),
    @Json(name = "textObjects")
    val textObjects: List<TextObject> = listOf(),
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "title")
    val title: String = "",
    @Json(name = "upc")
    val upc: String = "",
    @Json(name = "urls")
    val urls: List<Url> = listOf(),
    @Json(name = "variantDescription")
    val variantDescription: String = "",
    @Json(name = "variants")
    val variants: List<Variant> = listOf()
)