package com.zinc.antortestapp.ui.entries_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zinc.antortestapp.domain.common.Result
import com.zinc.antortestapp.domain.model.Entry
import com.zinc.antortestapp.domain.use_case.DeleteEntriesUseCase
import com.zinc.antortestapp.domain.use_case.GetEntriesUseCase
import com.zinc.antortestapp.ui.entries.EntryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EntriesEditViewModel @Inject constructor(
    private val getEntriesUseCase: GetEntriesUseCase,
    private val deleteEntriesUseCase: DeleteEntriesUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow<EntryEditListState>(EntryEditListState())
    val state = _state.asStateFlow()

    init {
        getEntries()
    }

    private fun getEntries() {
        getEntriesUseCase.execute()
            .subscribe { result ->
                when (result) {
                    is Result.Error -> _state.value =
                        EntryEditListState(error = result.message ?: "Unexpected error")
                    is Result.Success -> _state.value =
                        EntryEditListState(entries = result.data ?: emptyList())
                }
            }
    }

    fun deleteEntries() {
        deleteEntriesUseCase.execute(state.value.entries).subscribe {
            _state.value = EntryEditListState(entriesDeleted = true)
        }
    }

    fun navigated(){
        _state.value = EntryEditListState()
    }

}