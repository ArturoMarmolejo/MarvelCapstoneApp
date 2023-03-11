package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Price
import com.google.gson.Gson

class PriceTypeConverter {
    @TypeConverter
    fun fromPrice(price: Price): String {
        return Gson().toJson(price)
    }

    @TypeConverter
    fun toPrice(priceString: String): Price {
        return Gson().fromJson(priceString, Price::class.java)
    }
}