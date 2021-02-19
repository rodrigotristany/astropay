package com.rodrigotristany.astropay.domain.models

interface ErrorHandler {
    fun getError(throwable: Throwable?): ErrorModel
}