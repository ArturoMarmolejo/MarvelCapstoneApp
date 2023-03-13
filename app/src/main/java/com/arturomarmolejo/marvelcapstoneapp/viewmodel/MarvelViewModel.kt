package com.arturomarmolejo.marvelcapstoneapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResult
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResult
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllCharacters
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MarvelViewModel"
@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharacters,
    private val marvelRepository: MarvelRepository
): ViewModel() {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private var isInitialized = false

    var nameStartsWith: String? = null
    var titleStartsWith: String? = null

    lateinit var selectedCharacterItem: CharacterModel
    lateinit var selectedCreatorItem: CreatorResult
    lateinit var selectedComicItem: ComicsResult

    private val _allCharacters: MutableLiveData<UIState<List<CharacterModel>>> = MutableLiveData(UIState.LOADING)
    val allCharacters: MutableLiveData<UIState<List<CharacterModel>>> get() = _allCharacters

    private val _allCreators: MutableLiveData<UIState<CreatorResponse>> = MutableLiveData(UIState.LOADING)
    val allCreators: MutableLiveData<UIState<CreatorResponse>> get() = _allCreators

    private val _allComics: MutableLiveData<UIState<ComicsResponse>> = MutableLiveData(UIState.LOADING)
    val allComics: MutableLiveData<UIState<ComicsResponse>> get() = _allComics

    init {
        if(!isInitialized){
            getAllCharacters(nameStartsWith)
            getAllCreators(nameStartsWith)
            getAllComics(titleStartsWith)
            isInitialized = true
        }
    }


    fun getAllCharacters(nameStartsWithQuery: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            nameStartsWith = nameStartsWithQuery
            if(nameStartsWith != null) {
                getAllCharactersUseCase(nameStartsWith).collect { result ->
                    _allCharacters.postValue(result)
                }
            } else {
                getAllCharactersUseCase().collect { result ->
                    _allCharacters.postValue(result)
                }
            }
        }
    }

    fun getAllCreators(nameStartsWith: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            if(nameStartsWith != null) {
                marvelRepository.getAllCreators(nameStartsWith).collect { result ->
                    _allCreators.postValue(result)
                }
            } else {
                    marvelRepository.getAllCreators().collect{ result ->
                        _allCreators.postValue(result)
                }
            }
        }
    }

    fun getAllComics(titleStartsWith: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            if(titleStartsWith != null) {
                marvelRepository.getAllComics(titleStartsWith).collect { result ->
                    _allComics.postValue(result)
                }
            } else {
                    marvelRepository.getAllComics().collect{ result ->
                        _allComics.postValue(result)
                }
            }
        }
    }


}