package com.arturomarmolejo.marvelcapstoneapp.views.creator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.RvCreatorListFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.CreatorListAdapter

private const val TAG = "CreatorListFragment"
class CreatorListFragment: BaseFragment() {

    private val binding by lazy {
        RvCreatorListFragmentBinding.inflate(layoutInflater)
    }

    private val creatorListAdapter: CreatorListAdapter by lazy {
        CreatorListAdapter {
            marvelViewModel.selectedCreatorItem = it
            findNavController().navigate(R.id.action_creator_list_fragment_to_creatorDetailsFragment)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.creatorListRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = creatorListAdapter
        }

        marvelViewModel.allCreators.observe(viewLifecycleOwner){state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    creatorListAdapter.updateItems(state.response)

                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state")
                    }
                }
            }
        }

        binding.btnSearch.setOnClickListener {
            val searchQuery = binding.etSearch.text.toString().trim()
            if(searchQuery.isNotEmpty()) {
                marvelViewModel.getAllCreators(searchQuery)
            } else {
                marvelViewModel.getAllCreators(null)
            }
        }

        return binding.root
    }

}