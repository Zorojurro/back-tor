package com.example.domain.order.usecase

import com.example.domain.order.model.OrderModel
import com.example.domain.order.model.UserOrderModel
import com.example.domain.order.repository.OrderRepository
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend fun getAllOrders(): List<OrderModel> {
        return orderRepository.getAllOrders()
    }

    suspend fun getOrdersByUserId(userId: Int?): List<OrderModel> {
        orderRepository.getOrdersByUserId(userId!!).let {
            if (it.isEmpty()) {
                throw IllegalArgumentException("toto")
            } else {
                return it
            }
        }
    }

    suspend fun addOrder(order: OrderModel) {
        return orderRepository.addOrder(order)
    }

    suspend fun getOrderByName(userName: String): UserOrderModel {
        return orderRepository.getOrderByUserName(userName)
    }
}