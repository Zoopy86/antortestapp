package com.zinc.antortestapp.ui.entries

import com.zinc.antortestapp.domain.model.Entry

data class EntryListState(
    val isLoading: Boolean = false,
    val entries: List<Entry> = emptyList(),
    val error: String = "",
    val entriesDeleted: Boolean = false
)