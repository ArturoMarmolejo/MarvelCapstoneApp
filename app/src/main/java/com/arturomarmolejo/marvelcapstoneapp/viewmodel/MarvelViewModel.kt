package com.arturomarmolejo.marvelcapstoneapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel
import com.arturomarmolejo.marvelcapstoneapp.data.model.ComicModel
import com.arturomarmolejo.marvelcapstoneapp.data.model.CreatorModel
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResponse
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResult
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllCharactersUseCase
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllComicsUseCase
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllCreatorsUseCase
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MarvelViewModel"
@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getAllCreatorsUseCase: GetAllCreatorsUseCase,
    private val getAllComicsUseCase: GetAllComicsUseCase,
    private val marvelRepository: MarvelRepository
): ViewModel() {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private var isInitialized = false

    var nameStartsWith: String? = null
    var titleStartsWith: String? = null

    lateinit var selectedCharacterItem: CharacterModel
    lateinit var selectedCreatorItem: CreatorModel
    lateinit var selectedComicItem: ComicModel

    private val _allCharacters: MutableLiveData<UIState<List<CharacterModel>>> = MutableLiveData(UIState.LOADING)
    val allCharacters: MutableLiveData<UIState<List<CharacterModel>>> get() = _allCharacters

    private val _allCreators: MutableLiveData<UIState<List<CreatorModel>>> = MutableLiveData(UIState.LOADING)
    val allCreators: MutableLiveData<UIState<List<CreatorModel>>> get() = _allCreators

    private val _allComics:  MutableLiveData<UIState<List<ComicModel>>> = MutableLiveData(UIState.LOADING)
    val allComics:  MutableLiveData<UIState<List<ComicModel>>> get() = _allComics

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

    fun getAllCreators(nameStartsWithQuery: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            nameStartsWith = nameStartsWithQuery
            if(nameStartsWith != null) {
                getAllCreatorsUseCase(nameStartsWith).collect { result ->
                    _allCreators.postValue(result)
                }
            } else {
                getAllCreatorsUseCase().collect { result ->
                    _allCreators.postValue(result)
                }
            }
        }
    }

    fun getAllComics(titleStartsWithQuery: String? = null) {
        viewModelScope.launch(ioDispatcher) {
            titleStartsWith = titleStartsWithQuery
            if(titleStartsWith != null) {
              getAllComicsUseCase(titleStartsWith).collect { result ->
                  _allComics.postValue(result)
              }
            } else {
                getAllComicsUseCase().collect { result ->
                    _allComics.postValue(result)
                }

            }
        }
    }


}