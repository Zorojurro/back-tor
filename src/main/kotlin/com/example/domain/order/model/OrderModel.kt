package com.example.domain.order.model

import kotlinx.serialization.Serializable


@Serializable
data class OrderModel(
    val userId: Int,
    val item: String,
    val quantity: Int
)