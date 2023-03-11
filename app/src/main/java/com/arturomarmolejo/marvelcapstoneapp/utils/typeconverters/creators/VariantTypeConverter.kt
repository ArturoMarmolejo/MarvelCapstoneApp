package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Stories
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Variant
import com.google.gson.Gson

class VariantTypeConverter {

    @TypeConverter
    fun fromVariants(variant: Variant): String {
        return Gson().toJson(variant)
    }

    @TypeConverter
    fun toStories(variantString: String): Variant {
        return Gson().fromJson(variantString, Variant::class.java)
    }
}