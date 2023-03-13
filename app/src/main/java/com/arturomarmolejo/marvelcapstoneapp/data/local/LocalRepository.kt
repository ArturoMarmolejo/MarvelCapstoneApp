package com.arturomarmolejo.marvelcapstoneapp.data.local

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CreatorEntity
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel
import com.arturomarmolejo.marvelcapstoneapp.data.model.mapFromEntityToCharacter
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

interface LocalRepository {

    suspend fun insertCharacters(characters: List<CharacterEntity>?)
    fun getAllLocalCharacters(): Flow<UIState<List<CharacterModel>>>
    fun searchCharactersByName(nameStartWith: String): Flow<UIState<List<CharacterModel>>>

    suspend fun insertCreators(creators: List<CreatorEntity>?)
     suspend fun getAllLocalCreators(): List<CreatorEntity>
    suspend fun searchCreatorsByName(nameStartsWith: String): List<CreatorEntity>

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

    override suspend fun getAllLocalCreators(): List<CreatorEntity> {
        return marvelDAO.getAllLocalCreators()
    }

    override suspend fun searchCreatorsByName(nameStartsWith: String): List<CreatorEntity> {
        return marvelDAO.searchCreatorsByName(nameStartsWith)
    }

}

