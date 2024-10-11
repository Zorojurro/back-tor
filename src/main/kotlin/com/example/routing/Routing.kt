package com.example.routing

import com.example.domain.di.DaggerAppComponent
import com.example.presentation.order.controller.OrderController
import com.example.presentation.user.controller.UsersController
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    val appComponent = DaggerAppComponent.create()
    val userController = UsersController(
        usersUseCase = appComponent.createUserUseCase(),
        logger = appComponent.logger()
    )
    val orderController = OrderController(
        orderUseCase = appComponent.createOrderUseCase(),
        logger = appComponent.logger()
    )
    routing {
        userController.addUsersRoutes(this)
        orderController.addOrderRoutes(this)
    }
}

fun Application.contentNegotiation() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}
