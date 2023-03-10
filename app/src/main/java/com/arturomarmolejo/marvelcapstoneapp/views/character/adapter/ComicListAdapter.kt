package com.arturomarmolejo.marvelcapstoneapp.views.character.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.ComicItemBinding
import com.arturomarmolejo.marvelcapstoneapp.model.comic.ComicsResult
import com.bumptech.glide.Glide

private const val TAG = "ComicListAdapter"
class ComicListAdapter(
    private val itemSet: MutableList<ComicsResult> = mutableListOf(),
    private val onItemClick: (previewComicCard: ComicsResult) -> Unit
): RecyclerView.Adapter<ComicViewHolder>() {

    fun updateItems(newItems: List<ComicsResult>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
            ComicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size


}

class ComicViewHolder(
    private val binding: ComicItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ComicsResult, onItemClick: (ComicsResult) -> Unit) {
        binding.comicName.text = item.title
        binding.comicSeries.text = item.series.name
        Log.d(TAG, "bind item: ${item}, Series Name: ${item.series.name}, Series Title: ${item.title}, Thumbnail: ${item.thumbnail.path} ")

        itemView.setOnClickListener {
            onItemClick(item)
        }

        Glide
            .with(binding.root)
            .load(item.thumbnail.path + "." + item.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_menu_book_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.comicThumbnail)
    }
}