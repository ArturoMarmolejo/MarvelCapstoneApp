package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Stories
import com.google.gson.Gson

class StoriesTypeConverter {
    @TypeConverter
    fun fromStories(stories: Stories): String {
        return Gson().toJson(stories)
    }

    @TypeConverter
    fun toStories(storiesString: String): Stories {
        return Gson().fromJson(storiesString, Stories::class.java)
    }
}