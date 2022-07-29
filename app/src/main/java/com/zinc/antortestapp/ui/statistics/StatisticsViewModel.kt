package com.zinc.antortestapp.ui.statistics

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import com.zinc.antortestapp.domain.common.Result
import com.zinc.antortestapp.domain.use_case.GetEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val getEntriesUseCase: GetEntriesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(StatisticsState())
    val state = _state.asStateFlow()

    init {
        getEntries()
    }

    private fun getEntries() {
        _state.value = StatisticsState(isLoading = true)
        getEntriesUseCase.execute().subscribe({ result ->
            Log.e("StatisticsVM", "Result ${result.data}")
            when (result) {
                is Result.Error -> _state.value =
                    StatisticsState(error = result.message ?: "Unexpected error")
                is Result.Success -> {
                    result.data?.let { items ->
                        _state.value =
                            StatisticsState(
                                totalEntriesCount = items.count(),
                                firstEntryTimestamp = items.first().timestamp,
                                lastEntryTimestamp = items.last().timestamp
                            )
                        Log.e("StatisticsVM", "Result ${_state.value}")
                    }

                }
            }
        }, {
            _state.value =
                StatisticsState()
        })
    }

    fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp
        val date = DateFormat.format("dd-MM-yyyy HH:mm", calendar).toString()
        return date
    }

}