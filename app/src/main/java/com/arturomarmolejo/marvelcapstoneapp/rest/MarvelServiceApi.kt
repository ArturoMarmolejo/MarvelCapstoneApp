package com.arturomarmolejo.marvelcapstoneapp.rest

import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import retrofit2.Response
import retrofit2.http.GET
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

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val CHARACTER = "characters"
        private const val CREATOR = "creators"
        private const val COMICS = "comics"
    }
}