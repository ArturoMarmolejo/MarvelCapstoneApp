package com.arturomarmolejo.marvelcapstoneapp.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.CharacterItemDetailsBinding
import com.arturomarmolejo.marvelcapstoneapp.databinding.EventItemGeneralBinding
import com.arturomarmolejo.marvelcapstoneapp.model.character.CharacterResult
import com.arturomarmolejo.marvelcapstoneapp.model.events.EventResult
import com.bumptech.glide.Glide

private const val TAG = "CharacterListGeneralAda"
class CharacterListGeneralAdapter(
    private val itemSet: MutableList<CharacterResult> = mutableListOf(),
    private val onItemClick: (previewCharacterCard: CharacterResult) -> Unit
): RecyclerView.Adapter<CharacterListGeneralViewHolder>() {
    fun updateItems(newItems: List<CharacterResult>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            Log.d(TAG, "updateItems: itemSet: $itemSet, newItems: $newItems")

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListGeneralViewHolder {
        return CharacterListGeneralViewHolder(
            CharacterItemDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterListGeneralViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size
}

class CharacterListGeneralViewHolder(
    private val binding: CharacterItemDetailsBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterResult, onItemClick: (CharacterResult) -> Unit) {
        binding.characterName.text = item.name


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
            .into(binding.characterThumbnail)
    }
}