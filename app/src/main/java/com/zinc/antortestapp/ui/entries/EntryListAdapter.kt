package com.zinc.antortestapp.ui.entries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zinc.antortestapp.databinding.ItemEntryBinding
import com.zinc.antortestapp.domain.model.Entry

class EntryListAdapter(private val entryList: List<Entry>) : RecyclerView.Adapter<EntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EntryViewHolder(ItemEntryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(entryList.get(position))
    }

    override fun getItemCount(): Int = entryList.size
}