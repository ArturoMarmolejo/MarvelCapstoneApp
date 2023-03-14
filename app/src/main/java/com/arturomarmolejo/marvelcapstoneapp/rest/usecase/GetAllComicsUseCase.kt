package com.arturomarmolejo.marvelcapstoneapp.rest.usecase

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.data.local.LocalRepository
import com.arturomarmolejo.marvelcapstoneapp.data.model.ComicModel
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.utils.NetworkState
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "GetAllComics"
class GetAllComicsUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val marvelRepository: MarvelRepository,
    private val networkState: NetworkState
) {
    operator fun invoke(titleStartsWith: String? = null): Flow<UIState<List<ComicModel>>> {
        Log.d(TAG, "invoke: GetAll Comics Use Case ")
        if(titleStartsWith != null) {
            return if (!networkState.isInternetOn()) {
                Log.d(TAG, "invoke: Get All LOCAL Comics Use Case nameStartsWith NOT NULL")
                localRepository.searchComicsByTitle(titleStartsWith)
            } else {
                Log.d(TAG, "invoke: Get All API Comics Use Case nameStartsWith NULL")
                marvelRepository.getAllComics(titleStartsWith)
            }
        } else {
            return if (!networkState.isInternetOn()) {
                Log.d(TAG, "invoke: Get All LOCAL Comics Use Case titleStartsWith NOT NULL")
                localRepository.getAllLocalComics()
            } else {
                Log.d(TAG, "invoke: Get All API Comics Use Case titleStartsWith NULL")
                marvelRepository.getAllComics()
            }
        }

    }
}