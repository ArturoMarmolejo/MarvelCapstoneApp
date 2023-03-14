package com.arturomarmolejo.marvelcapstoneapp.rest

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.data.local.MarvelDAO
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.mapToCharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.mapToComicEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.mapToCreatorEntity
import com.arturomarmolejo.marvelcapstoneapp.data.model.*
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.FailureResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.NullCharacterListResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.NullCreatorListResponse
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MarvelRepository"
interface MarvelRepository {
    fun getAllCharacters(nameStartsWith: String? = null): Flow<UIState<List<CharacterModel>>>
    fun getAllCreators(nameStartsWith: String? = null): Flow<UIState<List<CreatorModel>>>
    fun getAllComics(titleStartsWith: String? = null): Flow<UIState<List<ComicModel>>>
}

class MarvelRepositoryImpl @Inject constructor(
    private val marvelServiceApi: MarvelServiceApi,
    private val marvelDAO: MarvelDAO
): MarvelRepository {
    override fun getAllCharacters(nameStartsWith: String?): Flow<UIState<List<CharacterModel>>> = flow {
        emit(UIState.LOADING)
        try {
            val response= if(nameStartsWith != null) {
                marvelServiceApi.getAllCharacters(nameStartsWith)
            } else marvelServiceApi.getAllCharacters()
            if(response.isSuccessful) {
                response.body()?.let {
                    val characterInfos = it.data.characterResults
                    Log.d(TAG, "getAllCharacters Inserting characters in database: $characterInfos")
                    marvelDAO.insertCharacters(characterInfos.mapToCharacterEntity())
                    val newCharacterInfos = if(nameStartsWith == null ) {
                        Log.d(TAG, "getAllCharacters from DAO")
                        marvelDAO.getAllLocalCharacters()
                    } else marvelDAO.searchCharactersByName(nameStartsWith)
                    Log.d(TAG, "getAllCharacters SUCCESS $newCharacterInfos")
                    emit(UIState.SUCCESS(newCharacterInfos.mapFromEntityToCharacter()))

                } ?: throw NullCharacterListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getAllCreators(nameStartsWith: String?): Flow<UIState<List<CreatorModel>>> = flow {
        emit(UIState.LOADING)
            try {
                val response = if(nameStartsWith != null) {
                    marvelServiceApi.getAllCreators(nameStartsWith)
                } else marvelServiceApi.getAllCreators()
                if(response.isSuccessful) {
                    response.body()?.let {
                        val creatorInfos = it.data.creatorResults
                        Log.d(TAG, "getAllCreators Inserting creator in database: $creatorInfos")
                        marvelDAO.insertCreators(creatorInfos.mapToCreatorEntity())
                        val newCreatorInfos = if(nameStartsWith == null ) {
                            Log.d(TAG, "getAllCreators from DAO")
                            marvelDAO.getAllLocalCreators()
                        } else marvelDAO.searchCreatorsByName(nameStartsWith)
                        Log.d(TAG, "getAllCreators SUCCESS $newCreatorInfos")
                        emit(UIState.SUCCESS(newCreatorInfos.mapFromEntityToCreator()))
                } ?: throw NullCreatorListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getAllComics(titleStartsWith: String?): Flow<UIState<List<ComicModel>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = if(titleStartsWith != null) {
                marvelServiceApi.getAllComics(titleStartsWith)
            } else marvelServiceApi.getAllComics()
            if(response.isSuccessful) {
                response.body()?.let {
                    val comicInfos = it.data.comicsResults
                    Log.d(TAG, "getAllComics Inserting comic in database: $comicInfos")
                    marvelDAO.insertComics(comicInfos.mapToComicEntity())
                    val newComicInfos = if(titleStartsWith == null ) {
                        Log.d(TAG, "getAllComics from DAO")
                        marvelDAO.getAllLocalComics()
                    } else marvelDAO.searchComicsByTitle(titleStartsWith)
                    Log.d(TAG, "getAllCreators SUCCESS $newComicInfos")
                    emit(UIState.SUCCESS(newComicInfos.mapFromEntityToComic()))
                } ?: throw NullCreatorListResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}

