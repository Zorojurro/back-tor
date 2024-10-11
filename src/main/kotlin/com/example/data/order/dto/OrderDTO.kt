package com.example.data.order.dto

data class OrderDTO(
    val id: Int?,
    val userId: Int,
    val item: String,
    val quantity: Int
)