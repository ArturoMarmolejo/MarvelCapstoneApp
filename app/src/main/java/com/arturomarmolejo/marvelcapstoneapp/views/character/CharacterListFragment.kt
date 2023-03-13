package com.arturomarmolejo.marvelcapstoneapp.views.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.RvCharacterListFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.arturomarmolejo.marvelcapstoneapp.utils.UIState
import com.arturomarmolejo.marvelcapstoneapp.views.character.adapter.CharacterListAdapter

private const val TAG = "CharacterListFragment"

class CharacterListFragment: BaseFragment() {
    private val binding by lazy {
        RvCharacterListFragmentBinding.inflate(layoutInflater)
    }

    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter {
            marvelViewModel.selectedCharacterItem = it
            findNavController().navigate(R.id.action_character_list_fragment_to_characterDetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.characterListRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = characterListAdapter
        }


        marvelViewModel.allCharacters.observe(viewLifecycleOwner){state ->
            when(state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    characterListAdapter.updateItems(state.response)
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {
                        Log.d(TAG, "onCreateView: UIState Error: $state ")
                    }
                }
            }
        }
        binding.btnSearch.setOnClickListener {
            val searchQuery = binding.etSearch.text.toString().trim()
            if(searchQuery.isNotEmpty()) {
                marvelViewModel.getAllCharacters(searchQuery)
            } else {
                marvelViewModel.getAllCharacters(null)
            }

        }
        return binding.root
    }




}