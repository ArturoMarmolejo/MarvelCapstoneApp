package com.arturomarmolejo.marvelcapstoneapp.views.creator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.CreatorDetailsFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.response.creator.CreatorResult
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.bumptech.glide.Glide

class CreatorDetailsFragment(): BaseFragment() {

    private val binding by lazy {
        CreatorDetailsFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var selectedCreator: CreatorResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectedCreator = marvelViewModel.selectedCreatorItem
        binding.creatorName.text = selectedCreator.fullName
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