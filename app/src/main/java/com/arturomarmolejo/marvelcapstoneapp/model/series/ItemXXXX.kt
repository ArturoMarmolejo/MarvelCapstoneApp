package com.arturomarmolejo.marvelcapstoneapp.model.series


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXXXX(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "type")
    val type: String = ""
)