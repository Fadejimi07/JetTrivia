package com.example.jettrivia.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jettrivia.domain.resource.DataResult
import com.example.jettrivia.domain.resource.PasswordError
import com.example.jettrivia.domain.validators.UserDataValidator

class UserViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    fun onRegisterClick(password: String) {
        when (val result = userDataValidator.validatePassword(password)) {
            is DataResult.Error -> {
                when (result.error) {
                    PasswordError.NO_DIGIT -> TODO()
                    PasswordError.TOO_SHORT -> TODO()
                    PasswordError.NO_UPERcASE -> TODO()
                }
            }

            is DataResult.Success -> {

            }
        }
    }
}