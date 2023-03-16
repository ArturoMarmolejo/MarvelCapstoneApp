package com.arturomarmolejo.marvelcapstoneapp.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.marvelcapstoneapp.R
import com.arturomarmolejo.marvelcapstoneapp.databinding.SeriesItemGeneralBinding
import com.arturomarmolejo.marvelcapstoneapp.model.series.SeriesResult
import com.bumptech.glide.Glide

private const val TAG = "SeriesListByCharacterAd"
class SeriesListGeneralAdapter(
    private val itemSet: MutableList<SeriesResult> = mutableListOf(),
    private val onItemClick: (previewCharacterCard: SeriesResult) -> Unit
): RecyclerView.Adapter<SeriesListByCharacterViewHolder>() {
    fun updateItems(newItems: List<SeriesResult>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            Log.d(TAG, "updateItems: itemSet: $itemSet, newItems: $newItems")

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesListByCharacterViewHolder {
        return SeriesListByCharacterViewHolder(
            SeriesItemGeneralBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SeriesListByCharacterViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

    override fun getItemCount(): Int = itemSet.size
}

class SeriesListByCharacterViewHolder(
    private val binding: SeriesItemGeneralBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SeriesResult, onItemClick: (SeriesResult) -> Unit) {
        binding.seriesName.text = item.title
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
            .into(binding.seriesThumbnail)
    }
}