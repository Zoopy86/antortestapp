package com.zinc.antortestapp.ui.entries_edit

import android.app.Dialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zinc.antortestapp.R
import com.zinc.antortestapp.databinding.FragmentEntriesEditBinding
import com.zinc.antortestapp.ui.entries.EntryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntriesEditFragment : Fragment(R.layout.fragment_entries_edit) {

    companion object {
        fun newInstance() = EntriesEditFragment()
    }

    private val viewModel: EntriesEditViewModel by viewModels()
    private var _binding: FragmentEntriesEditBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEntriesEditBinding.bind(view)
        subscribeObservers()
        createMenu()
    }

    private fun subscribeObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.recyclerView.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                } else if (state.entriesDeleted) {
                    findNavController().navigate(R.id.action_mainFragment_to_statisticsFragment)
                    viewModel.navigated()
                } else {
                    binding.progressBar.visibility = View.GONE
                    if (state.entries.isNotEmpty()) {
                        binding.tvEmpty.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.recyclerView.adapter = EntryEditListAdapter(state.entries)
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

    private fun createMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.entry_edit_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.actionDelete -> {
                        showAlertDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setPositiveButton("Ok",
                DialogInterface.OnClickListener { _, _ ->
                    viewModel.deleteEntries()
                })
            setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
            setMessage("Are you sure you want to delete entries?")
            setTitle("Warning")
            create()
            show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}