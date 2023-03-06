package com.arturomarmolejo.marvelcapstoneapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResult
import com.arturomarmolejo.marvelcapstoneapp.response.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.response.comic.ComicsResult
import com.arturomarmolejo.marvelcapstoneapp.response.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.response.creator.CreatorResult
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
    lateinit var selectedCreatorItem: CreatorResult
    lateinit var selectedComicItem: ComicsResult

    private val _allCharacters: MutableLiveData<UIState<CharacterResponse>> = MutableLiveData(UIState.LOADING)
    val allCharacters: MutableLiveData<UIState<CharacterResponse>> get() = _allCharacters

    private val _allCreators: MutableLiveData<UIState<CreatorResponse>> = MutableLiveData(UIState.LOADING)
    val allCreators: MutableLiveData<UIState<CreatorResponse>> get() = _allCreators

    private val _allComics: MutableLiveData<UIState<ComicsResponse>> = MutableLiveData(UIState.LOADING)
    val allComics: MutableLiveData<UIState<ComicsResponse>> get() = _allComics

    init {
        if(!isInitialized){
            getAllCharacters()
            getAllCreators()
            getAllComics()
            isInitialized = true
        }
    }

//    init {
//        getAllCreators()
//        getAllCreators()
//        getAllCharacters()
//    }

    private fun getAllCharacters() {
        viewModelScope.launch(ioDispatcher) {
            marvelRepository.getAllCharacters().collect{ result ->
                _allCharacters.postValue(result)
                Log.d(TAG, "getAllCharacters ViewModel: $_allCharacters ")
            }
        }
    }

    private fun getAllCreators() {
        viewModelScope.launch(ioDispatcher) {
            marvelRepository.getAllCreators().collect{ result ->
                _allCreators.postValue(result)
            }
        }
    }

    private fun getAllComics() {
        viewModelScope.launch(ioDispatcher) {
            marvelRepository.getAllComics().collect{ result ->
                _allComics.postValue(result)
            }
        }
    }

}