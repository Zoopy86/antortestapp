package com.zinc.antortestapp.ui.statistics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zinc.antortestapp.R
import com.zinc.antortestapp.databinding.FragmentStatisticsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.sql.Time
import java.util.*


@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val TAG = "StatisticsFragment"

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private val viewModel: StatisticsViewModel by viewModels()
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStatisticsBinding.bind(view)
        subscribeObservers()
    }

    fun subscribeObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect() { state ->
                if (state.isLoading) {
                    Log.e(TAG, "State: Is loading")
                    binding.progressBar.visibility = View.VISIBLE
                } else if (state.error.isNotBlank()) {
                    Log.e(TAG, "State: has error")
                    binding.progressBar.visibility = View.GONE
                    binding.tvEmpty.visibility = View.VISIBLE
                } else {
                    Log.e(TAG, "State: has data, ${state.totalEntriesCount}")
                    binding.progressBar.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    binding.tvTotalCount.text =
                        getString(R.string.statistics_total_count, state.totalEntriesCount)
                    state.firstEntryTimestamp?.let { timestamp ->
                        binding.tvFirstEntryTime.text =
                            getString(
                                R.string.statistics_first_entry_time,
                                viewModel.getDate(timestamp)
                            )
                    }
                    state.lastEntryTimestamp?.let { timestamp ->
                        binding.tvLastEntryTime.text =
                            getString(
                                R.string.statistics_last_entry_time,
                                viewModel.getDate(timestamp)
                            )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}