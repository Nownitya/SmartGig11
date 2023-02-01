package com.smartgig.utils

sealed class NetworkResult<out T> {
    data class Success<out T>
        (val data: T? = null) : NetworkResult<T>()

    data class Loading
        (val nothing: Nothing? = null) : NetworkResult<Nothing>()
    data class Error
        (val msg: String?): NetworkResult<Nothing>()

}