package com.arturomarmolejo.marvelcapstoneapp.rest.usecase

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.data.local.LocalRepository
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.utils.NetworkState
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "GetAllCharacters"
class GetAllCharactersUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val marvelRepository: MarvelRepository,
    private val networkState: NetworkState
){
    operator fun invoke(nameStartsWith: String? = null): Flow<UIState<List<CharacterModel>>> {
        Log.d(TAG, "invoke: GetAll Characters Use Case ")
        if(nameStartsWith != null) {
            return if (!networkState.isInternetOn()) {
                Log.d(TAG, "invoke: Get All LOCAL Characters Use Case nameStartsWith NOT NULL")
                localRepository.searchCharactersByName(nameStartsWith)
            } else {
                Log.d(TAG, "invoke: Get All API Characters Use Case nameStartsWith NULL")
                marvelRepository.getAllCharacters(nameStartsWith)
            }
        } else {
            return if (!networkState.isInternetOn()) {
                Log.d(TAG, "invoke: Get All LOCAL Characters Use Case nameStartsWith NOT NULL")
                localRepository.getAllLocalCharacters()
            } else {
                Log.d(TAG, "invoke: Get All API Characters Use Case nameStartsWith NULL")
                marvelRepository.getAllCharacters()
            }
        }

    }
}