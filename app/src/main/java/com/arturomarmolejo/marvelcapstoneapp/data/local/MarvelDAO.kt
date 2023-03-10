package com.arturomarmolejo.marvelcapstoneapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CharacterEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.ComicEntity
import com.arturomarmolejo.marvelcapstoneapp.data.local.entities.CreatorEntity
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel

@Dao
interface MarvelDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(character: List<CharacterEntity>?)

    @Query("SELECT * from characters")
    suspend fun getAllLocalCharacters(): List<CharacterEntity>

    @Query("SELECT * from characters WHERE name like '%' || :nameStartsWith || '%' ")
    suspend fun searchCharactersByName(nameStartsWith: String? = null): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreators(creators: List<CreatorEntity>?)

    @Query("SELECT * from creators")
    suspend fun getAllLocalCreators(): List<CreatorEntity>

    @Query("SELECT * from creators WHERE fullName like '%' || :nameStartsWith || '%'")
    suspend fun searchCreatorsByName(nameStartsWith: String? = null): List<CreatorEntity>

    @Query("SELECT * from comics")
    suspend fun getAllLocalComics(): List<ComicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comic: List<ComicEntity>?)

    @Query("SELECT * from comics WHERE title like '%' || :titleStartsWith || '%'")
    suspend fun searchComicsByTitle(titleStartsWith: String): List<ComicEntity>

}