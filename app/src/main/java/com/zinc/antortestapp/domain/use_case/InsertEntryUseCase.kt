package com.zinc.antortestapp.domain.use_case

import com.zinc.antortestapp.domain.model.Entry
import com.zinc.antortestapp.domain.repository.EntryRepository
import com.zinc.antortestapp.ui.new_entry.NewEntryState
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InsertEntryUseCase @Inject constructor(private val entryRepository: EntryRepository) {

    fun execute(entry: Entry): Single<Unit>{
        return Single
            .fromCallable {
                entryRepository.insertEntry(entry)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}