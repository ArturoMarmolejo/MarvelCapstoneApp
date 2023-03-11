package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Comics
import com.arturomarmolejo.marvelcapstoneapp.model.comic.CollectedIssue
import com.google.gson.Gson

class CollectedIssuesTypeConverter {
    @TypeConverter
    fun fromCollectedIssues(collectedIssue: CollectedIssue): String {
        return Gson().toJson(collectedIssue)
    }

    @TypeConverter
    fun toCollectedIssues(collectedIssuesString: String): CollectedIssue {
        return Gson().fromJson(collectedIssuesString, CollectedIssue::class.java)
    }

}