package com.zinc.antortestapp.domain.use_case

import com.zinc.antortestapp.domain.common.Result
import com.zinc.antortestapp.domain.model.Entry
import com.zinc.antortestapp.domain.repository.EntryRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetEntriesUseCase @Inject constructor(private val entryRepository: EntryRepository) {

    fun execute(): Flowable<Result<List<Entry>>> {
        return entryRepository.getEntries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Result.Success(it) as Result<List<Entry>> }
            .onErrorReturn { Result.Error("IO Unexpected error") }
    }

}