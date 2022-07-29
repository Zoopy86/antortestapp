package com.zinc.antortestapp.ui.entries_edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zinc.antortestapp.databinding.ItemEntryEditBinding
import com.zinc.antortestapp.domain.model.Entry

class EntryEditListAdapter(private val entryList: List<Entry>) :
    RecyclerView.Adapter<EntryEditViewHolder>(), EntryEditViewHolder.OnItemClickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryEditViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EntryEditViewHolder(ItemEntryEditBinding.inflate(inflater, parent, false), this)
    }

    override fun onBindViewHolder(holder: EntryEditViewHolder, position: Int) {
        holder.bind(entryList.get(position), position)
    }

    override fun getItemCount(): Int = entryList.size

    override fun onItemClick(position: Int) {
        notifyItemChanged(position)
    }
}