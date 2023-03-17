package com.arturomarmolejo.marvelcapstoneapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel
import com.arturomarmolejo.marvelcapstoneapp.data.model.ComicModel
import com.arturomarmolejo.marvelcapstoneapp.data.model.CreatorModel
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResponse
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResult
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResult
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResponse
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.arturomarmolejo.marvelcapstoneapp.model.events.EventResponse
import com.arturomarmolejo.marvelcapstoneapp.model.events.EventResult
import com.arturomarmolejo.marvelcapstoneapp.model.series.SeriesResponse
import com.arturomarmolejo.marvelcapstoneapp.model.series.SeriesResult
import com.arturomarmolejo.marvelcapstoneapp.rest.MarvelRepository
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllCharactersUseCase
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllComicsUseCase
import com.arturomarmolejo.marvelcapstoneapp.rest.usecase.GetAllCreatorsUseCase
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    var selectedCharacterId: String? = ""
    var selectedCreatorId: Int = 0
    var selectedComicId: Int = 0

    lateinit var selectedCharacterItem: CharacterModel
    lateinit var selectedCreatorItem: CreatorModel
    lateinit var selectedComicItem: ComicModel

    lateinit var selectedCharacterSeries: SeriesResult
    lateinit var selectedCharacterEvents: EventResult

    lateinit var selectedCreatorSeries: SeriesResult
    lateinit var selectedCreatorEvents: EventResult
    lateinit var selectedComicCharacter: CharacterResult
    lateinit var selectedComicCreator: CreatorResult


    private val _allCharacters: MutableLiveData<UIState<List<CharacterModel>>> = MutableLiveData(UIState.LOADING)
    val allCharacters: MutableLiveData<UIState<List<CharacterModel>>> get() = _allCharacters

    private val _allCreators: MutableLiveData<UIState<List<CreatorModel>>> = MutableLiveData(UIState.LOADING)
    val allCreators: MutableLiveData<UIState<List<CreatorModel>>> get() = _allCreators

    private val _allComics:  MutableLiveData<UIState<List<ComicModel>>> = MutableLiveData(UIState.LOADING)
    val allComics:  MutableLiveData<UIState<List<ComicModel>>> get() = _allComics

    private val _allSeriesByCharacter: MutableLiveData<UIState<SeriesResponse>> = MutableLiveData(UIState.LOADING)
    val allSeriesByCharacter: LiveData<UIState<SeriesResponse>> get() = _allSeriesByCharacter

    private val _allEventsByCharacter: MutableLiveData<UIState<EventResponse>> = MutableLiveData(UIState.LOADING)
    val allEventsByCharacter: LiveData<UIState<EventResponse>> get() = _allEventsByCharacter

    private val _allSeriesByCreator: MutableLiveData<UIState<SeriesResponse>> = MutableLiveData(UIState.LOADING)
    val allSeriesByCreator: LiveData<UIState<SeriesResponse>> get() = _allSeriesByCreator

    private val _allEventsByCreator: MutableLiveData<UIState<EventResponse>> = MutableLiveData(UIState.LOADING)
    val allEventsByCreator: LiveData<UIState<EventResponse>> get() = _allEventsByCreator

    private val _allCharactersByComic: MutableLiveData<UIState<CharacterResponse>> = MutableLiveData(UIState.LOADING)
    val allCharactersByComic: LiveData<UIState<CharacterResponse>> get() = _allCharactersByComic

    private val _allCreatorsByComic: MutableLiveData<UIState<CreatorResponse>> = MutableLiveData(UIState.LOADING)
    val allCreatorsByComic: LiveData<UIState<CreatorResponse>> get() = _allCreatorsByComic
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

    fun getAllSeriesByCharacterId(characterId: String?) {
        viewModelScope.launch(ioDispatcher) {
            selectedCharacterId = characterId
            marvelRepository.getAllSeriesByCharacterId(selectedCharacterId).collect{ result ->
                _allSeriesByCharacter.postValue(result)
            }
        }
    }

    fun getAllEventsByCharacterId(characterId: String?) {
        viewModelScope.launch(ioDispatcher) {
            selectedCharacterId = characterId
            marvelRepository.getAllEventsByCharacterId(selectedCharacterId).collect{ result ->
                _allEventsByCharacter.postValue(result)
            }
        }
    }

    fun getAllSeriesByCreatorId(creatorId: Int) {
        viewModelScope.launch(ioDispatcher) {
            selectedCreatorId = creatorId
            marvelRepository.getAllSeriesByCreatorId(selectedCreatorId).collect{ result ->
                _allSeriesByCreator.postValue(result)
            }
        }
    }

    fun getAllEventsByCreatorId(creatorId: Int) {
        viewModelScope.launch(ioDispatcher) {
            selectedCreatorId = creatorId
            marvelRepository.getAllEventsByCreatorId(selectedCreatorId).collect{ result ->
                _allEventsByCreator.postValue(result)
            }
        }
    }

    fun getAllCharactersByComicId(comicId: Int) {
        viewModelScope.launch(ioDispatcher) {
            selectedComicId = comicId
            marvelRepository.getAllCharactersByComicId(selectedComicId).collect{ result ->
                _allCharactersByComic.postValue(result)
            }
        }
    }

    fun getAllCreatorsByComicId(comicId: Int) {
        viewModelScope.launch(ioDispatcher) {
            selectedComicId = comicId
            marvelRepository.getAllCreatorsByComicId(selectedComicId).collect{ result ->
                _allCreatorsByComic.postValue(result)
            }
        }
    }


}