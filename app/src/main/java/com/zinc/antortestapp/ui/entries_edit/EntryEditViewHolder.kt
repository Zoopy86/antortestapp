package com.zinc.antortestapp.ui.entries_edit

import androidx.recyclerview.widget.RecyclerView
import com.zinc.antortestapp.databinding.ItemEntryEditBinding
import com.zinc.antortestapp.domain.model.Entry

class EntryEditViewHolder(val binding: ItemEntryEditBinding, val listener: OnItemClickListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(entry: Entry, position: Int) {
        binding.tvName.text = entry.name
        binding.tvEmail.text = entry.email
        binding.tvPhone.text = entry.phone
        binding.checkbox.isChecked = entry.selected
        itemView.setOnClickListener {
            entry.selected = !entry.selected
            listener.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}