package com.zinc.antortestapp.ui.entries

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zinc.antortestapp.databinding.ItemEntryBinding
import com.zinc.antortestapp.domain.model.Entry

class EntryViewHolder(val binding: ItemEntryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(entry: Entry) {
        binding.tvName.text = entry.name
        binding.tvEmail.text = entry.email
        binding.tvPhone.text = entry.phone
    }

}