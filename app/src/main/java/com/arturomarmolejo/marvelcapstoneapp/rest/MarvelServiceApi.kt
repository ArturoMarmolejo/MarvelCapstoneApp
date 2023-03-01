package com.arturomarmolejo.marvelcapstoneapp.rest

import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelServiceApi.Companion.CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//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
interface MarvelServiceApi {
    @GET(CHARACTER)
    suspend fun getAllCharacters(
        @Query("limit") limit: Int = 20
    ): Response<CharacterResponse>

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val CHARACTER = "characters"
    }
}