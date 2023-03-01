package com.arturomarmolejo.marvelcapstoneapp.rest

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.FailureResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.NullCharacterListResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MarvelRepository"
interface MarvelRepository {
    fun getAllCharacters(): Flow<UIState<CharacterResponse>>
}

class MarvelRepositoryImpl @Inject constructor(
    private val marvelServiceApi: MarvelServiceApi
): MarvelRepository {
    override fun getAllCharacters(): Flow<UIState<CharacterResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response: Response<CharacterResponse> = marvelServiceApi.getAllCharacters()
            if(response.isSuccessful) {
                response.body()?.let {
                    val temp = UIState.SUCCESS(it)
                    emit(temp)
                    Log.d(TAG, "getAllCharacters: $it")
                } ?: throw NullCharacterListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}

