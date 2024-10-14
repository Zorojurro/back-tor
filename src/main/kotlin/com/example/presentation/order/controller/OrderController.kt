package com.example.presentation.order.controller

import com.example.data.order.database.Orders.userId
import com.example.domain.core.Logger
import com.example.domain.order.model.OrderModel
import com.example.domain.order.usecase.OrderUseCase
import com.example.domain.users.usecase.UsersUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import javax.inject.Inject

class OrderController @Inject constructor(
    private val orderUseCase: OrderUseCase,
    private val logger: Logger
) {
    fun addOrderRoutes(route: Route) {
        route.route("api/v1/order") {
            get {
                logger.info("Received GET request to /api/v1/order")
                val order = orderUseCase.getAllOrders()
                call.respond(order)
            }
            post {
                val params = call.receive<OrderModel>()
                orderUseCase.addOrder(params)
                call.respondText("User added")
            }
            get("/{userId}") {
                val userId = call.parameters["userId"] ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing id parameter"
                )
                logger.info("Received GET request to /api/v1/order/$userId")
                val user = orderUseCase.getOrdersByUserId(userId.toInt())
                if (user.isNotEmpty()) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                }
            }
            get("/user/{userName}") {
                val userName = call.parameters["userName"] ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing name parameter"
                )
                logger.info("Received GET request to /api/v1/order/$userName")
                val user = orderUseCase.getOrderByName(userName)
                call.respond(user)
            }
        }
    }
}