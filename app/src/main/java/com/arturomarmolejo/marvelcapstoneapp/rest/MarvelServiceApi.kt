package com.arturomarmolejo.marvelcapstoneapp.rest

import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelServiceApi.Companion.CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelServiceApi {
    @GET(CHARACTER)
    suspend fun getAllCharacters(
       // @Query("limit") limit: Int = 20
    ): Response<CharacterResponse>

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val CHARACTER = "characters"
    }
}