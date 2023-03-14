package com.arturomarmolejo.marvelcapstoneapp.rest.usecase

import android.util.Log
import com.arturomarmolejo.marvelcapstoneapp.data.local.LocalRepository
import com.arturomarmolejo.marvelcapstoneapp.data.model.CreatorModel
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.utils.NetworkState
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "GetAllCreators"
class GetAllCreatorsUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val marvelRepository: MarvelRepository,
    private val networkState: NetworkState
) {
    operator fun invoke(nameStartsWith: String? = null): Flow<UIState<List<CreatorModel>>> {
        Log.d(TAG, "invoke: GetAll Creators Use Case ")
        if(nameStartsWith != null) {
            return if (!networkState.isInternetOn()) {
                Log.d(TAG, "invoke: Get All LOCAL Creators Use Case nameStartsWith NOT NULL")
                localRepository.searchCreatorsByName(nameStartsWith)
            } else {
                Log.d(TAG, "invoke: Get All API Creators Use Case nameStartsWith NULL")
                marvelRepository.getAllCreators(nameStartsWith)
            }
        } else {
            return if (!networkState.isInternetOn()) {
                Log.d(TAG, "invoke: Get All LOCAL Creators Use Case nameStartsWith NOT NULL")
                localRepository.getAllLocalCreators()
            } else {
                Log.d(TAG, "invoke: Get All API Creators Use Case nameStartsWith NULL")
                marvelRepository.getAllCreators()
            }
        }

    }
}