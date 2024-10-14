package com.example.presentation.user.controller

import com.example.domain.core.ErrorHandlerRepository
import com.example.domain.core.Logger
import com.example.domain.users.usecase.UsersUseCase
import com.example.safeExecute
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import javax.inject.Inject


class UsersController @Inject constructor(
    private val usersUseCase: UsersUseCase,
    private val logger: Logger,
    private val exceptionHandlerRepository : ErrorHandlerRepository
) {
    fun addUsersRoutes(route: Route) {
        route.route("api/v1/users") {
            get {
                call.safeExecute(exceptionHandler = exceptionHandlerRepository) {
                    logger.info("Received GET request to /api/v1/users")
                    val users = usersUseCase.getAllUser()
                    call.respond(users)
                }
            }
            post {
                val params = call.receive<Map<String, String>>()
                val firstName =
                    params["firstName"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing first name")
                val lastName =
                    params["lastName"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing last name")
                usersUseCase.addUser(firstName, lastName)
                call.respondText("User added")
            }
            get("/{name}") {
                val name = call.parameters["name"] ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing name parameter"
                )
                logger.info("Received GET request to /api/v1/users/$name")
                val user = usersUseCase.getUserByLastName(name)
                if (user.isNotEmpty()) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                }
            }
        }
    }
}