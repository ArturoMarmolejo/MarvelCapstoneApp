package com.arturomarmolejo.marvelcapstoneapp.rest

import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.response.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.response.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelServiceApi.Companion.CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelServiceApi {
    @GET(CHARACTER)
    suspend fun getAllCharacters(
    ): Response<CharacterResponse>

    @GET(CREATOR)
    suspend fun getAllCreators(
    ): Response<CreatorResponse>

    @GET(CREATOR)
    suspend fun getAllComics(
    ): Response<ComicsResponse>

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val CHARACTER = "characters"
        private const val CREATOR = "creators"
        private const val COMICS = "comics"
    }
}