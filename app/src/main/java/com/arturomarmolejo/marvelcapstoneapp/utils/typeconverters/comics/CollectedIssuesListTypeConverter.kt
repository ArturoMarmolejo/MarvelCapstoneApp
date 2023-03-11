package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.arturomarmolejo.marvelcapstoneapp.model.comic.CollectedIssue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectedIssuesListTypeConverter {
    @TypeConverter
    fun fromCollectedIssuesList(collectedIssuesList: List<CollectedIssue>): String {
        return Gson().toJson(collectedIssuesList)
    }

    @TypeConverter
    fun toCollectedIssuesList(collectedIssuesListString: String): List<CollectedIssue> {
        val collectedIssuesListType = object : TypeToken<List<CollectedIssue>>() {}.type
        return Gson().fromJson(collectedIssuesListString, collectedIssuesListType)
    }
}