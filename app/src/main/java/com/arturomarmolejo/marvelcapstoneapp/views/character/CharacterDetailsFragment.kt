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
import com.arturomarmolejo.marvelcapstoneapp.views.character.adapter.SeriesListByCharacterAdapter
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


        selectedCharacter = marvelViewModel.selectedCharacterItem

        //http://gateway.marvel.com/v1/public/series/13082
        val url = selectedCharacter.series.collectionURI
        val regex = Regex("/(\\d+)/[^/]*$") // match "/digits/" followed by any characters except slash till the end of the string
        val matchResult = regex.find(url)
        val extractedString = matchResult?.groupValues?.get(1)

        selectedCharacterId = extractedString

        marvelViewModel.selectedCharacterId = selectedCharacterId

        marvelViewModel.getAllSeriesByCharacterId(selectedCharacterId)

        characterDetailsBinding.characterName.text = selectedCharacter.name
        characterDetailsBinding.tvCharacterDescription.text = selectedCharacter.description

        Glide
            .with(characterDetailsBinding.root)
            .load(selectedCharacter.thumbnail.path + "." + selectedCharacter.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.baseline_person_off_24)
            .into(characterDetailsBinding.characterThumbnail)

        Log.d(TAG, "onCreateView: Character Details Display ${selectedCharacter.series.collectionURI}, $selectedCharacterId," +
                "$url" +
                ", $regex," +
                "$matchResult," +
                "$extractedString ")

        return characterDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}

