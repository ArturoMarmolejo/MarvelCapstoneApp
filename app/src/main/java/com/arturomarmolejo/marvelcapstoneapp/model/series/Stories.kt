package com.arturomarmolejo.marvelcapstoneapp.model.series


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stories(
    @Json(name = "available")
    val available: Int = 0,
    @Json(name = "collectionURI")
    val collectionURI: String = "",
    @Json(name = "items")
    val items: List<ItemXXXX> = listOf(),
    @Json(name = "returned")
    val returned: Int = 0
)