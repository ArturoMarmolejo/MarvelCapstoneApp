package com.arturomarmolejo.marvelcapstoneapp.model.comic


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemX(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "role")
    val role: String = ""
)