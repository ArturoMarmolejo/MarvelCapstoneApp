package com.arturomarmolejo.marvelcapstoneapp.views.comic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.ComicDetailsFragmentBinding
import com.arturomarmolejo.marvelcapstoneapp.response.comic.ComicsResult
import com.arturomarmolejo.marvelcapstoneapp.utils.BaseFragment
import com.bumptech.glide.Glide

class ComicDetailsFragment(): BaseFragment() {
    private val binding by lazy {
        ComicDetailsFragmentBinding.inflate(layoutInflater)
    }

    private lateinit var selectedComic: ComicsResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectedComic = marvelViewModel.selectedComicItem
        binding.comicName.text = selectedComic.title

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