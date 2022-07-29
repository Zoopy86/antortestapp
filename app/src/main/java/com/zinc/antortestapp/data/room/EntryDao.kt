package com.zinc.antortestapp.data.room

import androidx.room.*
import com.zinc.antortestapp.data.model.EntryDto
import io.reactivex.Flowable

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: EntryDto)

    @Update
    fun update(entry: EntryDto)

    @Delete
    fun delete(entry: EntryDto)

    @Query("SELECT * FROM entries")
    fun getEntryList(): Flowable<List<EntryDto>>

    @Query("DELETE FROM entries WHERE id in (:entryIds)")
    fun deleteEntries(entryIds: List<Int>)

}