package com.arturomarmolejo.marvelcapstoneapp.views.creator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.data.model.CreatorModel
import com.arturomarmolejo.marvelcapstoneapp.databinding.CreatorDetailsFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.EventListGeneralAdapter
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.SeriesListGeneralAdapter
import com.bumptech.glide.Glide

private const val TAG = "CreatorDetailsFragment"
class CreatorDetailsFragment(): BaseFragment() {

    private val binding by lazy {
        CreatorDetailsFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var selectedCreator: CreatorModel
    private var selectedCreatorId: Int = 0

    private val seriesListGeneralAdapter: SeriesListGeneralAdapter by lazy {
        SeriesListGeneralAdapter {
            marvelViewModel.selectedCreatorSeries = it
        }
    }

    private val eventListGeneralAdapter: EventListGeneralAdapter by lazy {
        EventListGeneralAdapter {
            marvelViewModel.selectedCreatorEvents = it
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        marvelViewModel.allSeriesByCreator.observe(viewLifecycleOwner) {state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "onCreateView: Series Items: ${state.response.data.seriesResults}")
                        seriesListGeneralAdapter.updateItems(state.response.data.seriesResults)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state")
                    }
                }
            }
        }

        marvelViewModel.allEventsByCreator.observe(viewLifecycleOwner) {state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    Log.d(TAG, "onCreateView: Creator Event Items: ${state.response.data.eventResults}")
                    eventListGeneralAdapter.updateItems(state.response.data.eventResults)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: Creator Event UIState Error: $state")
                    }
                }
            }
        }

        binding.rvSeriesListByCreator.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = seriesListGeneralAdapter
        }

        binding.rvEventListByCreator.apply{
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            adapter = eventListGeneralAdapter
        }

        selectedCreator = marvelViewModel.selectedCreatorItem
        val creatorId = selectedCreator.id

        selectedCreatorId = creatorId

        marvelViewModel.getAllSeriesByCreatorId(selectedCreatorId)
        marvelViewModel.getAllEventsByCreatorId(selectedCreatorId)

        binding.creatorName.text = selectedCreator.fullName
        binding.tvCreatorDescription.text = "Description yet to be added"


        Glide
            .with(binding.root)
            .load(selectedCreator.thumbnail.path + "." + selectedCreator.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.creatorThumbnail)

        return binding.root
    }
}