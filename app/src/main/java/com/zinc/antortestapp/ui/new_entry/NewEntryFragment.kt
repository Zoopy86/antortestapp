package com.zinc.antortestapp.ui.new_entry

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zinc.antortestapp.R
import com.zinc.antortestapp.databinding.FragmentNewEntryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewEntryFragment : Fragment(R.layout.fragment_new_entry) {

    companion object {
        fun newInstance() = NewEntryFragment()
    }

    private val viewModel: NewEntryViewModel by viewModels()

    private var _binding: FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewEntryBinding.bind(view)
        bindViews()
        subscribeObservers()
    }

    private fun bindViews() {
        binding.etName.addTextChangedListener(nameTextWatcher)
        binding.etEmail.addTextChangedListener(emailTextWatcher)
        binding.etPhone.addTextChangedListener(phoneTextWatcher)
    }

    override fun onDestroy() {
        binding.etName.removeTextChangedListener(nameTextWatcher)
        binding.etEmail.removeTextChangedListener(emailTextWatcher)
        binding.etPhone.removeTextChangedListener(phoneTextWatcher)
        _binding = null
        super.onDestroy()
    }

    private fun subscribeObservers() {

        binding.btnSave.setOnClickListener {
            viewModel.saveData()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                if (state.errorMessage.isNotBlank()) {
                    showToast(state.errorMessage)
                }
                if (state.isSaved) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private val nameTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            viewModel.setName(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    }

    private val emailTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            viewModel.setEmail(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    }

    private val phoneTextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            viewModel.setPhone(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}