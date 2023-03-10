package com.arturomarmolejo.marvelcapstoneapp.model.comic


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    @Json(name = "price")
    val price: Double = 0.0,
    @Json(name = "type")
    val type: String = ""
)