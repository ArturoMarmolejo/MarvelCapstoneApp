package com.arturomarmolejo.marvelcapstoneapp.model.events


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
    val eventResults: List<EventResult> = listOf(),
    @Json(name = "total")
    val total: Int = 0
)