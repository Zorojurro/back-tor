package com.example.domain.order.model

import kotlinx.serialization.Serializable


@Serializable
data class OrderModel(
    val userId: Int,
    val item: String,
    val quantity: Int
)

@Serializable
data class UserOrderModel(
    val userLastname: String,
    val userFirstname: String,
    val orders: List<OrderModel>
)