package com.example.data.core

import com.example.domain.core.ConsoleLogger
import com.example.domain.core.ErrorHandlerRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.uri
import io.ktor.server.response.respond
import kotlinx.serialization.Serializable


@Serializable
data class ErrorResponse(
    val statusCode: Int,
    val error: String,
    val message: String? = null
)

class DefaultErrorHandler(private val logger : ConsoleLogger) : ErrorHandlerRepository {

    override suspend fun handleException(call: ApplicationCall, exception: Throwable): HttpStatusCode {
        logger.error("Erreur sur la route : ${call.request.uri}, error is : ${exception.message}")
        val errorResponse = when (exception) {
            is IllegalArgumentException -> {
                ErrorResponse(
                    statusCode = HttpStatusCode.BadRequest.value,
                    error = "Bad Request",
                    message = exception.message
                )
            }
            is NoSuchElementException -> {
                ErrorResponse(
                    statusCode = HttpStatusCode.NotFound.value,
                    error = "Not Found",
                    message = exception.message
                )
            }
            else -> {
                ErrorResponse(
                    statusCode = HttpStatusCode.InternalServerError.value,
                    error = "Internal Server Error"
                )
            }
        }

        call.respond(HttpStatusCode.fromValue(errorResponse.statusCode), errorResponse)
        return HttpStatusCode.fromValue(errorResponse.statusCode)
    }
}
