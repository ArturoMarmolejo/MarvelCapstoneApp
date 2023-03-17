package com.arturomarmolejo.marvelcapstoneapp.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.CreatorItemDetailsBinding
import com.arturomarmolejo.marvelcapstoneapp.model.creator.CreatorResult
import com.bumptech.glide.Glide

private const val TAG = "CharacterListGeneralAda"
class CreatorListGeneralAdapter(
    private val itemSet: MutableList<CreatorResult> = mutableListOf(),
    private val onItemClick: (previewCreatorCard: CreatorResult) -> Unit
): RecyclerView.Adapter<CreatorListGeneralViewHolder>() {
    fun updateItems(newItems: List<CreatorResult>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            Log.d(TAG, "updateItems: itemSet: $itemSet, newItems: $newItems")

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorListGeneralViewHolder {
        return CreatorListGeneralViewHolder(
            CreatorItemDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CreatorListGeneralViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size
}

class CreatorListGeneralViewHolder(
    private val binding: CreatorItemDetailsBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CreatorResult, onItemClick: (CreatorResult) -> Unit) {
        binding.creatorName.text = item.fullName


        itemView.setOnClickListener {
            onItemClick(item)
        }

        Log.d(TAG, "bind: $item")

        Glide
            .with(binding.root)
            .load(item.thumbnail.path + "." + item.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.creatorThumbnail)
    }
}