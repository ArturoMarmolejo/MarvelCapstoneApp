package com.arturomarmolejo.marvelcapstoneapp.response.comic


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Creators(
    @Json(name = "available")
    val available: Int = 0,
    @Json(name = "collectionURI")
    val collectionURI: String = "",
    @Json(name = "items")
    val items: List<ItemX> = listOf(),
    @Json(name = "returned")
    val returned: Int = 0
)