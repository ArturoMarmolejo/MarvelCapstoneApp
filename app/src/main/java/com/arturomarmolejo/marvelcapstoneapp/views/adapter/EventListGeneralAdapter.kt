package com.arturomarmolejo.marvelcapstoneapp.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.EventItemGeneralBinding
import com.arturomarmolejo.marvelcapstoneapp.model.events.EventResult
import com.bumptech.glide.Glide

private const val TAG = "EventListByCharacterAda"
class EventListGeneralAdapter(
    private val itemSet: MutableList<EventResult> = mutableListOf(),
    private val onItemClick: (previewCharacterCard: EventResult) -> Unit
): RecyclerView.Adapter<EventListByCharacterViewHolder>() {
    fun updateItems(newItems: List<EventResult>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            Log.d(TAG, "updateItems: itemSet: $itemSet, newItems: $newItems")

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListByCharacterViewHolder {
        return EventListByCharacterViewHolder(
            EventItemGeneralBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventListByCharacterViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size
}

class EventListByCharacterViewHolder(
    private val binding: EventItemGeneralBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EventResult, onItemClick: (EventResult) -> Unit) {
        binding.eventName.text = item.title
//        binding.characterSeries.text = if(item.series.items.isEmpty()) "TBA" else item.series.items[0].name


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
            .into(binding.eventThumbnail)
    }
}