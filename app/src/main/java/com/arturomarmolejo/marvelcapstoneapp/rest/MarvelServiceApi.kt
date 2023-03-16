package com.arturomarmolejo.marvelcapstoneapp.rest

import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.model.events.EventResponse
import com.arturomarmolejo.marvelcapstoneapp.model.series.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelServiceApi {
    @GET(CHARACTER)
    suspend fun getAllCharacters(
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int = 20
    ): Response<CharacterResponse>

    @GET(CREATOR)
    suspend fun getAllCreators(
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int = 20
    ): Response<CreatorResponse>

    @GET(COMICS)
    suspend fun getAllComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("limit") limit: Int = 20
    ): Response<ComicsResponse>

    @GET("$CHARACTER/{characterId}/series")
    suspend fun getAllSeriesByCharacter(
        //@Query("nameStartsWith") nameStartsWith: String? = null,
        @Path("characterId") characterId: String?,
        @Query("limit") limit: Int = 10,
    ): Response<SeriesResponse>

    @GET("$CHARACTER/{characterId}/events")
    suspend fun getAllEventsByCharacter(
        //@Query("nameStartsWith") nameStartsWith: String? = null,
        @Path("characterId") characterId: String?,
        @Query("limit") limit: Int = 10,
    ): Response<EventResponse>

    @GET("$CREATOR/{creatorId}/series")
    suspend fun getAllSeriesByCreator(
        //@Query("nameStartsWith") nameStartsWith: String? = null,
        @Path("creatorId") creatorId: Int,
        @Query("limit") limit: Int = 10,
    ): Response<SeriesResponse>

    @GET("$CREATOR/{creatorId}/events")
    suspend fun getAllEventsByCreator(
        //@Query("nameStartsWith") nameStartsWith: String? = null,
        @Path("creatorId") creatorId: Int,
        @Query("limit") limit: Int = 10,
    ): Response<EventResponse>


    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val CHARACTER = "characters"
        private const val CREATOR = "creators"
        private const val COMICS = "comics"

        //http://gateway.marvel.com/v1/public/characters/1011198/series"
    }
}