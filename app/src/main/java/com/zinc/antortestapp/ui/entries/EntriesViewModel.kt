package com.zinc.antortestapp.ui.entries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zinc.antortestapp.domain.common.Result
import com.zinc.antortestapp.domain.use_case.GetEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EntriesViewModel @Inject constructor(private val getEntriesUseCase: GetEntriesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<EntryListState>(EntryListState())
    val state = _state.asStateFlow()

    init {
        getEntries()
    }

    private fun getEntries() {
        _state.value = EntryListState(isLoading = true)
        getEntriesUseCase.execute()
            .subscribe { result ->
                when (result) {
                    is Result.Error -> _state.value =
                        EntryListState(error = result.message ?: "Unexpected error")
                    is Result.Success -> _state.value =
                        EntryListState(entries = result.data ?: emptyList())
                }
            }
    }

}