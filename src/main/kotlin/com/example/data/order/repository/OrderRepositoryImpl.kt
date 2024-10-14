package com.example.data.order.repository

import com.example.data.order.database.OrderDao
import com.example.data.order.dto.OrderDTO
import com.example.data.order.dto.UserOrderDTO
import com.example.domain.order.model.OrderModel
import com.example.domain.order.model.UserOrderModel
import com.example.domain.order.repository.OrderRepository

class OrderRepositoryImpl(private val orderDao: OrderDao) : OrderRepository {
    override suspend fun getAllOrders(): List<OrderModel> {
        return orderDao.getAllOrders().map {
            OrderModel(
                userId = it.userId,
                item = it.item,
                quantity = it.quantity
            )
        }
    }

    override suspend fun getOrdersByUserId(userId: Int): List<OrderModel> {
        return orderDao.getOrdersByUserId(userId).map {
            OrderModel(
                userId = it.userId,
                item = it.item,
                quantity = it.quantity
            )
        }
    }

    override suspend fun addOrder(order: OrderModel) {
        val orderDto = OrderDTO(
            id = null,
            userId = order.userId,
            item = order.item,
            quantity = order.quantity
        )
        orderDao.addOrder(orderDto)
    }

    override suspend fun getOrderByUserName(userName: String): UserOrderModel {
        val dao = orderDao.getOrderByName(userName)
        return UserOrderModel(dao.userLastname, dao.userFirstname, dao.orders.map {
            OrderModel(it.userId, it.item, it.quantity)
        })
    }
}