package com.arturomarmolejo.marvelcapstoneapp.views.character.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.data.model.CreatorModel
import com.arturomarmolejo.marvelcapstoneapp.databinding.CreatorItemBinding
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.bumptech.glide.Glide

private const val TAG = "CreatorListAdapter"
class CreatorListAdapter(
    private val itemSet: MutableList<CreatorModel> = mutableListOf(),
    private val onItemClick:(previewCreatorCard: CreatorModel) ->Unit
): RecyclerView.Adapter<CreatorViewHolder>() {

    fun updateItems(newItems: List<CreatorModel>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder =
        CreatorViewHolder(
            CreatorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        return holder.bind(itemSet[position], onItemClick)
    }

    override fun getItemCount(): Int = itemSet.size
}

class CreatorViewHolder(
    private val binding: CreatorItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CreatorModel, onItemClick: (previewCreatorCard: CreatorModel) -> Unit) {
        binding.creatorName.text = item.fullName
        binding.creatorSeries.text = if (item.series.items.isEmpty()) "TBA" else item.series.items[0].name
        Log.d(TAG, "bind item: ${item}, Series Name: ${item.series.items}, Thumbnail: ${item.thumbnail.path} ")

        itemView.setOnClickListener {
            onItemClick(item)
        }

        Glide
            .with(binding.root)
            .load(item.thumbnail.path + "." + item.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_pin_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.creatorThumbnail)
    }

}