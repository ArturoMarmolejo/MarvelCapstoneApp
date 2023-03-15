package com.arturomarmolejo.marvelcapstoneapp.rest

import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.model.series.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelServiceApi {
    @GET(CHARACTER)
    suspend fun getAllCharacters(
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int = 100
    ): Response<CharacterResponse>

    @GET(CREATOR)
    suspend fun getAllCreators(
        @Query("nameStartsWith") nameStartsWith: String? = null,
        @Query("limit") limit: Int = 100
    ): Response<CreatorResponse>

    @GET(COMICS)
    suspend fun getAllComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("limit") limit: Int = 100
    ): Response<ComicsResponse>

    @GET("$CHARACTER/{characterId}/series")
    suspend fun getAllSeriesByCharacter(
        //@Query("nameStartsWith") nameStartsWith: String? = null,
        @Path("characterId") characterId: String?,
        @Query("limit") limit: Int = 20,
    ): Response<SeriesResponse>

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val CHARACTER = "characters"
        private const val CREATOR = "creators"
        private const val COMICS = "comics"
        private const val SERIES = "series"
        private const val ID = "{id}"

        //http://gateway.marvel.com/v1/public/characters/1011198/series"
    }
}