package com.clem.arch_core.utils

class ResponseThrowable(
        var errorCode: Int,
        var errorMessage: String,
        throwable: Throwable
) : Exception(throwable)