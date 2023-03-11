package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Stories
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