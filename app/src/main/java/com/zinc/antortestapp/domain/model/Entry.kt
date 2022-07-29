package com.zinc.antortestapp.domain.model

data class Entry(
    val id: Int?,
    val name: String,
    val email: String,
    val phone: String,
    val timestamp: Long
) {
    var selected: Boolean = false
}
