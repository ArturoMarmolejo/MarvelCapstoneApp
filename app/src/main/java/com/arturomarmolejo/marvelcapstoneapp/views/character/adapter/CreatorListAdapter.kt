package com.arturomarmolejo.marvelcapstoneapp.views.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.CreatorItemBinding
import com.arturomarmolejo.marvelcapstoneapp.response.creator.CreatorResult
import com.bumptech.glide.Glide

class CreatorListAdapter(
    private val itemSet: MutableList<CreatorResult> = mutableListOf(),
    private val onItemClick:(previewCreatorCard: CreatorResult) ->Unit
): RecyclerView.Adapter<CreatorViewHolder>() {

    fun updateItems(newItems: List<CreatorResult>) {
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

    fun bind(item: CreatorResult, onItemClick: (previewCreatorCard: CreatorResult) -> Unit) {
        binding.creatorName.text = item.fullName
        binding.creatorSeries.text = if (item.series.items.isEmpty()) "TBA" else item.series.items[0].name


        itemView.setOnClickListener {
            onItemClick(item)
        }

        Glide
            .with(binding.root)
            .load(item.thumbnail.path + "" + item.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_pin_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.creatorThumbnail)
    }

}