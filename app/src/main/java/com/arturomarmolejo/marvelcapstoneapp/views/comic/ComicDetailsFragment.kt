package com.arturomarmolejo.marvelcapstoneapp.views.comic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.data.model.ComicModel
import com.arturomarmolejo.marvelcapstoneapp.databinding.ComicDetailsFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResult
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.CharacterListGeneralAdapter
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.CreatorListGeneralAdapter
import com.bumptech.glide.Glide

private const val TAG = "ComicDetailsFragment"
class ComicDetailsFragment(): BaseFragment() {
    private val binding by lazy {
        ComicDetailsFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var selectedComic: ComicModel
    private var selectedComicId: Int = 0

    private val characterListGeneralAdapter: CharacterListGeneralAdapter by lazy {
        CharacterListGeneralAdapter {
            marvelViewModel.selectedComicCharacter = it
        }
    }

    private val creatorListGeneralAdapter: CreatorListGeneralAdapter by lazy {
        CreatorListGeneralAdapter {
            marvelViewModel.selectedComicCreator = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        marvelViewModel.allCharactersByComic.observe(viewLifecycleOwner) {state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "onCreateView: Series Items: ${state.response.data.characterResults} ")
                    characterListGeneralAdapter.updateItems(state.response.data.characterResults)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state")
                    }
                }
            }
        }

        marvelViewModel.allCreatorsByComic.observe(viewLifecycleOwner) {state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "onCreateView: Series Items: ${state.response.data.creatorResults} ")
                    creatorListGeneralAdapter.updateItems(state.response.data.creatorResults)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state")
                    }
                }
            }
        }

        binding.rvCharacterListByComic.apply{
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = characterListGeneralAdapter
        }

        binding.rvCreatorListByComic.apply{
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = creatorListGeneralAdapter
        }



        selectedComic = marvelViewModel.selectedComicItem
        val comicId = selectedComic.id
        selectedComicId = comicId

        marvelViewModel.getAllCharactersByComicId(selectedComicId)
        marvelViewModel.getAllCreatorsByComicId(selectedComicId)

        binding.comicName.text = selectedComic.title
        binding.tvComicDescription.text = selectedComic.description

        Glide
            .with(binding.root)
            .load(selectedComic.thumbnail.path + "." + selectedComic.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_menu_book_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.comicThumbnail)

        return binding.root
    }
}