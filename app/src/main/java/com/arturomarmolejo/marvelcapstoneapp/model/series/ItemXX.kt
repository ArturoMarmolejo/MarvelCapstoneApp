package com.arturomarmolejo.marvelcapstoneapp.model.series


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXX(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "role")
    val role: String = ""
)