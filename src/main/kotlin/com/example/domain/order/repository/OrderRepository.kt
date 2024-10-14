package com.example.domain.order.repository

import com.example.domain.order.model.OrderModel
import com.example.domain.order.model.UserOrderModel

interface OrderRepository {
    suspend fun getAllOrders(): List<OrderModel>
    suspend fun getOrdersByUserId(userId: Int): List<OrderModel>
    suspend fun addOrder(order: OrderModel)
    suspend fun getOrderByUserName(userName: String): UserOrderModel
}