package com.example.jettrivia.domain.resource

sealed interface DataError : Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
    }

    enum class Local : DataError {
        DISK_FREE
    }
}