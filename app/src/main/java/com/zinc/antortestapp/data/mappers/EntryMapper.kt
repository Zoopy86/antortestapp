package com.zinc.antortestapp.data.mappers

import com.zinc.antortestapp.data.model.EntryDto
import com.zinc.antortestapp.domain.model.Entry

fun EntryDto.toEntry(): Entry {
    return Entry(
        id = id,
        name = name,
        email = email,
        phone = phone,
        timestamp = timestamp
    )
}

fun Entry.toEntryDto(): EntryDto {
    return EntryDto(
        id = id,
        name = name,
        email = email,
        phone = phone,
        timestamp = timestamp
    )
}