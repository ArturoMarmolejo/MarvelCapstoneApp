package com.arturomarmolejo.marvelcapstoneapp.response.comic


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Variant(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = ""
)