package com.example.jettrivia.domain.validators

import com.example.jettrivia.domain.resource.DataResult
import com.example.jettrivia.domain.resource.Error

class UserDataValidator {
    fun validatePassword(password: String): DataResult<Unit, PasswordError> {
        if (password.length < 9) {
            return DataResult.Error(PasswordError.TOO_SHORT)
        }
        val hasUppercaseChar = password.any { it.isUpperCase() }
        if (hasUppercaseChar) {
            return DataResult.Error(PasswordError.NO_UPERcASE)
        }
        val hasDigit = password.any { it.isDigit() }
        if (hasDigit) {
            return DataResult.Error(PasswordError.NO_DIGIT)
        }

        return DataResult.Success(Unit)
    }

    enum class PasswordError : Error {
        TOO_SHORT,
        NO_UPERcASE,
        NO_DIGIT
    }
}

