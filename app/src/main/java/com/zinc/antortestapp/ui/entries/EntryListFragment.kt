package com.zinc.antortestapp.ui.entries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zinc.antortestapp.R
import com.zinc.antortestapp.databinding.FragmentEntriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryListFragment : Fragment(R.layout.fragment_entries) {

    companion object {
        fun newInstance() = EntryListFragment()
    }

    private val viewModel: EntriesViewModel by viewModels()
    private var _binding: FragmentEntriesBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEntriesBinding.bind(view)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.recyclerView.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    if (state.entries.isNotEmpty()) {
                        binding.tvEmpty.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.recyclerView.adapter = EntryListAdapter(state.entries)
                    } else {
                        binding.tvEmpty.visibility = View.VISIBLE
                        if (state.error.isNotBlank()) {
                            binding.tvEmpty.text = state.error
                        }
                        binding.recyclerView.visibility = View.GONE
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