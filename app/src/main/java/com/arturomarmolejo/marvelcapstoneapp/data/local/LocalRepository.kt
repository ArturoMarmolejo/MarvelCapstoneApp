package com.arturomarmolejo.marvelcapstoneapp.data.local

import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CreatorEntity
import javax.inject.Inject

interface LocalRepository {

    suspend fun insertCharacters(characters: List<CharacterEntity>?)
    suspend fun getAllLocalCharacters(): List<CharacterEntity>
    suspend fun searchCharactersByName(nameStartWith: String): List<CharacterEntity>?

    suspend fun insertCreators(creators: List<CreatorEntity>?)
    suspend fun getAllLocalCreators(): List<CreatorEntity>
    suspend fun searchCreatorsByName(nameStartsWith: String): List<CreatorEntity>

}

class LocalRepositoryImpl @Inject constructor(
    private val marvelDAO: MarvelDAO
) : LocalRepository {

    override suspend fun insertCharacters(characters: List<CharacterEntity>?) {
        marvelDAO.insertCharacters(characters)
    }

    override suspend fun getAllLocalCharacters(): List<CharacterEntity> {
        return marvelDAO.getAllLocalCharacters()
    }

    override suspend fun searchCharactersByName(nameStartWith: String): List<CharacterEntity>? {
        return marvelDAO.searchCharactersByName(nameStartWith)
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

