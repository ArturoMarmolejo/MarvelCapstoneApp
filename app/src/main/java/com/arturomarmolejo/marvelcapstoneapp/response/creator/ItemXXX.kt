package com.arturomarmolejo.marvelcapstoneapp.response.creator


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXXX(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "type")
    val type: String = ""
)