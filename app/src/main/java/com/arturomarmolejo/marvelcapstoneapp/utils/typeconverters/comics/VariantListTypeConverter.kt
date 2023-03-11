package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Url
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Variant
import com.google.errorprone.annotations.Var
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VariantListTypeConverter {
    @TypeConverter
    fun fromVariantList(variantList: List<Variant>): String {
        return Gson().toJson(variantList)
    }

    @TypeConverter
    fun toUrlList(variantListString: String): List<Variant> {
        val variantListType = object : TypeToken<List<Variant>>() {}.type
        return Gson().fromJson(variantListString, variantListType)
    }

}