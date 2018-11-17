package com.codylab.foodie.core.coroutine

import java.io.IOException

suspend fun <T : Any?> safeCall(
    errorMessage: String,
    call: suspend () -> Response<T>
): Response<T> {
    return try {
        call()
    } catch (e: Exception) {
        Response.Error(IOException(errorMessage, e))
    }
}