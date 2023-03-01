package com.arturomarmolejo.marvelcapstoneapp.model.character


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "limit")
    val limit: Int = 0,
    @Json(name = "offset")
    val offset: Int = 0,
    @Json(name = "results")
    val results: List<Result> = listOf(),
    @Json(name = "total")
    val total: Int = 0
)