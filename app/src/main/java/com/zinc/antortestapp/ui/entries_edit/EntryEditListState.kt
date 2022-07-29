package com.zinc.antortestapp.ui.entries_edit

import com.zinc.antortestapp.domain.model.Entry

data class EntryEditListState(
    val isLoading: Boolean = false,
    val entries: List<Entry> = emptyList(),
    val error: String = "",
    val entriesDeleted: Boolean = false
)