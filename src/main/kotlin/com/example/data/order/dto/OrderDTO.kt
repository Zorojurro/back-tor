package com.example.data.order.dto

data class OrderDTO(
    val id: Int?,
    val userId: Int,
    val item: String,
    val quantity: Int
)

data class UserOrderDTO(
    val userFirstname : String,
    val userLastname : String,
    val orders: List<OrderDTO>
)