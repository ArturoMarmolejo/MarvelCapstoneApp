package com.arturomarmolejo.marvelcapstoneapp.rest

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.FailureResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.NullCharacterListResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MarvelRepository"
interface MarvelRepository {
    fun getAllCharacters(nameStartsWith: String? = null): Flow<UIState<CharacterResponse>>
    fun getAllCreators(nameStartsWith: String? = null): Flow<UIState<CreatorResponse>>
    fun getAllComics(titleStartsWith: String? = null): Flow<UIState<ComicsResponse>>
}

class MarvelRepositoryImpl @Inject constructor(
    private val marvelServiceApi: MarvelServiceApi
): MarvelRepository {
    override fun getAllCharacters(nameStartsWith: String?): Flow<UIState<CharacterResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response: Response<CharacterResponse> = if(nameStartsWith != null) {
                marvelServiceApi.getAllCharacters(nameStartsWith)
            } else marvelServiceApi.getAllCharacters()
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

    override fun getAllCreators(nameStartsWith: String?): Flow<UIState<CreatorResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response: Response<CreatorResponse> = if(nameStartsWith != null) {
                marvelServiceApi.getAllCreators(nameStartsWith)
            } else marvelServiceApi.getAllCreators()
            if(response.isSuccessful) {
                response.body()?.let {
                    val temp = UIState.SUCCESS(it)
                    emit(temp)
                } ?: throw NullCharacterListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getAllComics(titleStartsWith: String?): Flow<UIState<ComicsResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response: Response<ComicsResponse> = if(titleStartsWith != null){
                marvelServiceApi.getAllComics(titleStartsWith)
            } else marvelServiceApi.getAllComics()
            if(response.isSuccessful) {
                response.body()?.let {
                    val temp = UIState.SUCCESS(it)
                    emit(temp)
                } ?: throw NullCharacterListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}

