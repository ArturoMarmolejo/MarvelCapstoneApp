package com.arturomarmolejo.marvelcapstoneapp.model.character


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Series(
    @Json(name = "available")
    val available: Int = 0,
    @Json(name = "collectionURI")
    val collectionURI: String = "",
    @Json(name = "items")
    val items: List<Item> = listOf(),
    @Json(name = "returned")
    val returned: Int = 0
)