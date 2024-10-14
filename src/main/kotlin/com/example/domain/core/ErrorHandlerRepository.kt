package com.example.domain.core

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall

interface ErrorHandlerRepository {
   suspend fun handleException(call: ApplicationCall, exception: Throwable): HttpStatusCode
}