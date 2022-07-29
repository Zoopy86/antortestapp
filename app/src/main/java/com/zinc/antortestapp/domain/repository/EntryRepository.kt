package com.zinc.antortestapp.domain.repository

import com.zinc.antortestapp.domain.model.Entry
import io.reactivex.Flowable

interface EntryRepository {

    fun getEntries(): Flowable<List<Entry>>

    fun insertEntry(entry: Entry)

    fun deleteEntries(entryIds: List<Int>)

}