package com.example.domain.order.usecase

import com.example.domain.order.model.OrderModel
import com.example.domain.order.repository.OrderRepository
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend fun getAllOrders(): List<OrderModel> {
        return orderRepository.getAllOrders()
    }

    suspend fun getOrdersByUserId(userId: Int): List<OrderModel> {
        return orderRepository.getOrdersByUserId(userId)
    }

    suspend fun addOrder(order: OrderModel) {
        return orderRepository.addOrder(order)
    }
}