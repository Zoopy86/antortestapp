package com.zinc.antortestapp.ui.new_entry

import androidx.lifecycle.ViewModel
import com.zinc.antortestapp.domain.model.Entry
import com.zinc.antortestapp.domain.repository.EntryRepository
import com.zinc.antortestapp.domain.use_case.InsertEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class NewEntryViewModel @Inject constructor(private val insertEntryUseCase: InsertEntryUseCase) :
    ViewModel() {

    private var name: String = ""
    private var email: String = ""
    private var phone: String = ""

    private val emailPattern = Pattern.compile(
        "[a-zA-Z0-9+._%-+]{1,256}"
                + "@"
                + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}"
                + "."
                + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}"
    )

    private val _state = MutableStateFlow(NewEntryState())
    val state = _state.asStateFlow()

    fun setName(name: String) {
        this.name = name
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun saveData() {
        if (checkInputs()) {
            val entry = Entry(null, name, email, phone, System.currentTimeMillis())
            insertEntryUseCase.execute(entry).subscribe(
                {
                    _state.value = NewEntryState(isSaved = true)
                }, { error ->
                    _state.value =
                        NewEntryState(errorMessage = error.message ?: "Unexpected error")
                }
            )
        }
    }

    private fun checkInputs(): Boolean {
        if (name.isBlank()) {
            _state.value = NewEntryState(errorMessage = "Name cannot be empty")
            return false
        }
        if (!emailPattern.matcher(email).matches()) {
            _state.value = NewEntryState(errorMessage = "Email cannot be empty")
            return false
        }
        if (phone.isBlank()) {
            _state.value = NewEntryState(errorMessage = "Phone cannot be empty")
            return false
        }
        return true
    }

}