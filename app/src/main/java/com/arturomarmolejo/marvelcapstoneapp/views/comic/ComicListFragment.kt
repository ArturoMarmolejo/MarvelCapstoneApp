package com.arturomarmolejo.marvelcapstoneapp.views.comic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.RvComicListFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import com.arturomarmolejo.marvelcapstoneapp.views.adapter.ComicListAdapter

private const val TAG = "ComicListFragment"
class ComicListFragment: BaseFragment() {
    private val binding by lazy {
        RvComicListFragmentBinding.inflate(layoutInflater)
    }

    private val comicListAdapter: ComicListAdapter by lazy {
        ComicListAdapter {
            marvelViewModel.selectedComicItem = it
            findNavController().navigate(R.id.action_comic_list_fragment_to_comicDetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.comicListRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = comicListAdapter
        }

        marvelViewModel.allComics.observe(viewLifecycleOwner){state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    comicListAdapter.updateItems(state.response)
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
                marvelViewModel.getAllComics(searchQuery)
            } else {
                marvelViewModel.getAllComics(null)
            }
        }
        return binding.root
    }
}