package com.arturomarmolejo.marvelcapstoneapp.views.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.CharacterDetailsFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResult
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.bumptech.glide.Glide

private const val TAG = "CharacterDetailsFragment"
class CharacterDetailsFragment(): BaseFragment() {
    private val binding by lazy {
        CharacterDetailsFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var selectedCharacter: CharacterResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        selectedCharacter = marvelViewModel.selectedCharacterItem
        binding.characterName.text = selectedCharacter.name
        Glide
            .with(binding.root)
            .load(selectedCharacter.thumbnail.path + "." + selectedCharacter.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.characterThumbnail)

        Log.d(TAG, "onCreateView: Character Details Display ${selectedCharacter.name} ")

        return binding.root
    }


}