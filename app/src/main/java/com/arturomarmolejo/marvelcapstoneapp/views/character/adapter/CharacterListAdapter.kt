package com.arturomarmolejo.marvelcapstoneapp.views.character.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.CharacterItemBinding
import com.arturomarmolejo.marvelcapstoneapp.response.character.CharacterResult
import com.bumptech.glide.Glide

private const val TAG = "CharacterListAdapter"
class CharacterListAdapter(
    private val itemSet: MutableList<CharacterResult> = mutableListOf(),
    private val onItemClick: (previewCharacterCard: CharacterResult) -> Unit
): RecyclerView.Adapter<CharacterViewHolder>() {
    fun updateItems(newItems: List<CharacterResult>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            Log.d(TAG, "updateItems: itemSet: $itemSet, newItems: $newItems")

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size
}

class CharacterViewHolder(
    private val binding: CharacterItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterResult, onItemClick: (CharacterResult) -> Unit) {
        binding.characterName.text = item.name
        binding.characterSeries.text = if(item.series.items.isEmpty()) "TBA" else item.series.items[0].name

        itemView.setOnClickListener {
            onItemClick(item)
        }

        Glide
            .with(binding.root)
            .load(item.thumbnail.path + "." + item.thumbnail.extension)
            .centerCrop()
            .placeholder(R.drawable.baseline_person_24)
            .error(R.drawable.baseline_person_off_24)
            .into(binding.characterThumbnail)
    }
}