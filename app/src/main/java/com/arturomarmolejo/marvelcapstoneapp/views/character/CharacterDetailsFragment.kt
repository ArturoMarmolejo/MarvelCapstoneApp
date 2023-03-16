package com.arturomarmolejo.marvelcapstoneapp.views.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.data.model.CharacterModel
import com.arturomarmolejo.marvelcapstoneapp.databinding.CharacterDetailsFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.EventListByCharacterAdapter
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.SeriesListByCharacterAdapter
import com.bumptech.glide.Glide

private const val TAG = "CharacterDetailsFragment"
class CharacterDetailsFragment(): BaseFragment() {
    private val characterDetailsBinding by lazy {
        CharacterDetailsFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var selectedCharacter:CharacterModel
     private var selectedCharacterId: String? = ""


    private val seriesListByCharacterAdapter: SeriesListByCharacterAdapter by lazy {
        SeriesListByCharacterAdapter {
            marvelViewModel.selectedCharacterSeries = it
        }
    }

    private val eventListByCharacterAdapter: EventListByCharacterAdapter by lazy {
        EventListByCharacterAdapter {
            marvelViewModel.selectedCharacterEvents = it
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        marvelViewModel.allSeriesByCharacter.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "onCreateView: Series Items: ${state.response.data.seriesResults}")
                    seriesListByCharacterAdapter.updateItems(state.response.data.seriesResults)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state")
                    }
                }
            }
        }


        characterDetailsBinding.rvSeriesListByCharacter.apply{
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = seriesListByCharacterAdapter
        }

        marvelViewModel.allEventsByCharacter.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "onCreateView: Event Items: ${state.response.data.eventResults}")
                    eventListByCharacterAdapter.updateItems(state.response.data.eventResults)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state")
                    }
                }
                else -> {
                    Log.d(TAG, "onCreateView: UNIDENTIFIED EXCEPTION")
                }
            }
        }

        characterDetailsBinding.rvEventListByCharacter.apply{
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = eventListByCharacterAdapter
        }



        selectedCharacter = marvelViewModel.selectedCharacterItem

        //http://gateway.marvel.com/v1/public/series/13082
        val seriesUrl = selectedCharacter.series.collectionURI
        val seriesRegex = Regex("/(\\d+)/[^/]*$") // match "/digits/" followed by any characters except slash till the end of the string
        val seriesMatchResult = seriesRegex.find(seriesUrl)
        val characterIdExtractedString = seriesMatchResult?.groupValues?.get(1)

        selectedCharacterId = characterIdExtractedString

        marvelViewModel.selectedCharacterId = selectedCharacterId

        marvelViewModel.getAllSeriesByCharacterId(selectedCharacterId)
        marvelViewModel.getAllEventsByCharacterId(selectedCharacterId)

        characterDetailsBinding.characterName.text = selectedCharacter.name
        characterDetailsBinding.tvCharacterDescription.text = if(selectedCharacter.description != "") selectedCharacter.description else "Description yet to be added"

        Glide
            .with(characterDetailsBinding.root)
            .load(selectedCharacter.thumbnail.path + "." + selectedCharacter.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.baseline_person_off_24)
            .into(characterDetailsBinding.characterThumbnail)

        Log.d(TAG, "onCreateView: Character Details Display ${selectedCharacter.series.collectionURI}, $selectedCharacterId," +
                "$seriesUrl" +
                ", $seriesRegex," +
                "$seriesMatchResult," +
                "$characterIdExtractedString ")

        return characterDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}

