package com.arturomarmolejo.marvelcapstoneapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResult
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MarvelViewModel"
@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository
): ViewModel() {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private var isInitialized = false

    lateinit var selectedCharacterItem: CharacterResult

    private val _allCharacters: MutableLiveData<UIState<CharacterResponse>> = MutableLiveData(UIState.LOADING)
    val allCharacters: MutableLiveData<UIState<CharacterResponse>> get() = _allCharacters

    init {
        if(!isInitialized){
            getAllCharacters()
            isInitialized = true
        }
    }

    private fun getAllCharacters() {
        viewModelScope.launch(ioDispatcher) {
            marvelRepository.getAllCharacters().collect{ result ->
                _allCharacters.postValue(result)
                Log.d(TAG, "getAllCharacters ViewModel: $_allCharacters ")
            }
        }
    }

}