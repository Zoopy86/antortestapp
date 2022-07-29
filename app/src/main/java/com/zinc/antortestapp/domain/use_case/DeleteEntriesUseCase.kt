package com.zinc.antortestapp.domain.use_case

import com.zinc.antortestapp.domain.model.Entry
import com.zinc.antortestapp.domain.repository.EntryRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeleteEntriesUseCase @Inject constructor(private val entryRepository: EntryRepository) {

    fun execute(entries: List<Entry>): Completable {
        return Completable.fromAction {
            val list = entries.filter { it.selected && it.id != null }.map { it.id!! }
            entryRepository.deleteEntries(list)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//        Observable.fromIterable(entries)
//            .filter {
//                it.selected && it.id != null
//            }
//            .map {
//                it.id!!
//            }
//            .toList()
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                {
//                    entryRepository.deleteEntries(it)
//                },
//                {}
//            )
    }

}