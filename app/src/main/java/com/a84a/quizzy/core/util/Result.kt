package com.a84a.quizzy.core.util

sealed class  Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}
