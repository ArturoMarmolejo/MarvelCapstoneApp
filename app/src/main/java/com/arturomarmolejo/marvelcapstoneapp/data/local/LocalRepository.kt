package com.arturomarmolejo.marvelcapstoneapp.data.local

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.ComicEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CreatorEntity
import com.arturomarmolejo.marvelcapstoneapp.data.model.*
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

interface LocalRepository {

    suspend fun insertCharacters(characters: List<CharacterEntity>?)
    fun getAllLocalCharacters(): Flow<UIState<List<CharacterModel>>>
    fun searchCharactersByName(nameStartWith: String): Flow<UIState<List<CharacterModel>>>

    suspend fun insertCreators(creators: List<CreatorEntity>?)
    fun getAllLocalCreators(): Flow<UIState<List<CreatorModel>>>
    fun searchCreatorsByName(nameStartsWith: String): Flow<UIState<List<CreatorModel>>>

    suspend fun insertComics(creators: List<ComicEntity>?)
    fun getAllLocalComics(): Flow<UIState<List<ComicModel>>>
    fun searchComicsByTitle(titleStartsWith: String):  Flow<UIState<List<ComicModel>>>

}

private const val TAG = "LocalRepository"
class LocalRepositoryImpl @Inject constructor(
    private val marvelDAO: MarvelDAO
) : LocalRepository {

    override suspend fun insertCharacters(characters: List<CharacterEntity>?) {
        marvelDAO.insertCharacters(characters)
    }

    override fun getAllLocalCharacters(): Flow<UIState<List<CharacterModel>>> = flow {
        try {
            Log.d(TAG, "getAllLocalCharacters: Fetching data from local database ")
            val characterInfo = marvelDAO.getAllLocalCharacters()
            emit(UIState.SUCCESS(characterInfo.mapFromEntityToCharacter()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }

    }

    override fun searchCharactersByName(nameStartWith: String ): Flow<UIState<List<CharacterModel>>> = flow {
        try {
            Log.d(TAG, "Search Character By Name: Fetching data from local database ")
            val characterInfo = marvelDAO.searchCharactersByName(nameStartWith)
            emit(UIState.SUCCESS(characterInfo.mapFromEntityToCharacter()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override suspend fun insertCreators(creators: List<CreatorEntity>?) {
        return marvelDAO.insertCreators(creators)
    }

    override fun getAllLocalCreators(): Flow<UIState<List<CreatorModel>>> = flow {
        try {
            Log.d(TAG, "getAllLocalCreators: Fetching data from local database ")
            val creatorInfo = marvelDAO.getAllLocalCreators()
            emit(UIState.SUCCESS(creatorInfo.mapFromEntityToCreator()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun searchCreatorsByName(nameStartsWith: String):  Flow<UIState<List<CreatorModel>>> = flow {
        try {
            Log.d(TAG, "Search Character By Name: Fetching data from local database ")
            val creatorInfo = marvelDAO.searchCreatorsByName(nameStartsWith)
            emit(UIState.SUCCESS(creatorInfo.mapFromEntityToCreator()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }


    override fun getAllLocalComics(): Flow<UIState<List<ComicModel>>> = flow {
        try {
            Log.d(TAG, "getAllLocalComics: Fetching data from local database ")
            val comicInfo = marvelDAO.getAllLocalComics()
            emit(UIState.SUCCESS(comicInfo.mapFromEntityToComic()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override suspend fun insertComics(comics: List<ComicEntity>?) {
        return marvelDAO.insertComics(comics)
    }

    override fun searchComicsByTitle(titleStartsWith: String ): Flow<UIState<List<ComicModel>>> = flow {
        try {
            Log.d(TAG, "Search Comic By Name: Fetching data from local database ")
            val comicInfo = marvelDAO.searchComicsByTitle(titleStartsWith)
            emit(UIState.SUCCESS(comicInfo.mapFromEntityToComic()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}

