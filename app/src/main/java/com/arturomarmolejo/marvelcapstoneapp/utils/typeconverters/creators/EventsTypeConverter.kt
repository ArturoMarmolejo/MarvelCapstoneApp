package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Events

import com.google.gson.Gson


class EventsTypeConverter {
    @TypeConverter
    fun fromEvents(events: Events): String {
        return Gson().toJson(events)
    }

    @TypeConverter
    fun toEvents(eventsString: String): Events {
        return Gson().fromJson(eventsString, Events::class.java)
    }
}