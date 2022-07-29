package com.zinc.antortestapp.data.repository

import com.zinc.antortestapp.data.mappers.toEntry
import com.zinc.antortestapp.data.mappers.toEntryDto
import com.zinc.antortestapp.data.room.EntryDao
import com.zinc.antortestapp.domain.model.Entry
import com.zinc.antortestapp.domain.repository.EntryRepository
import io.reactivex.Flowable
import javax.inject.Inject

class EntryRepositoryImpl @Inject constructor(private val entryDao: EntryDao) : EntryRepository {

    override fun getEntries(): Flowable<List<Entry>> {
        return entryDao.getEntryList().map { it.map { it.toEntry() } }
    }

    override fun insertEntry(entry: Entry) {
        entryDao.insert(entry.toEntryDto())
    }

    override fun deleteEntries(entryIds: List<Int>) {
        entryDao.deleteEntries(entryIds)
    }
}