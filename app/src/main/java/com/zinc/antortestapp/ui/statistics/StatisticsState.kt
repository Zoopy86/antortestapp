package com.zinc.antortestapp.ui.statistics

data class StatisticsState(
    val isLoading: Boolean = false,
    val totalEntriesCount: Int = 0,
    val firstEntryTimestamp: Long? = null,
    val lastEntryTimestamp: Long? = null,
    val error: String = ""
)