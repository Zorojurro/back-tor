package com.example.data.order.database

import com.example.data.infra.DatabaseModule
import com.example.data.order.dto.OrderDTO
import com.example.data.order.dto.UserOrderDTO
import com.example.data.user.database.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class OrderDao @Inject constructor(private val databaseModule: DatabaseModule) {

    init {
        transaction {
            SchemaUtils.create(Orders)
        }
    }

    fun getOrdersByUserId(userId: Int): List<OrderDTO> {
        return databaseModule.dbTransaction {
            Orders.select { Orders.userId eq userId }
                .map {
                    OrderDTO(
                        id = it[Orders.id],
                        userId = it[Orders.userId],
                        item = it[Orders.product],
                        quantity = it[Orders.quantity]
                    )
                }
        }
    }

    fun getOrderByName(userName: String): UserOrderDTO {
        return databaseModule.dbTransaction {
            val orderList: MutableList<OrderDTO> = mutableListOf()
            var firstName = ""
            var lastName = ""
            val query = (Users innerJoin Orders).slice(
                Users.firstName,
                Users.lastName,
                Orders.userId,
                Orders.id,
                Orders.product,
                Orders.quantity
            ).select { Users.lastName eq userName }
            query.forEach {
                firstName = it[Users.firstName]
                lastName = it[Users.lastName]
                orderList.add(
                    OrderDTO(
                        id = it[Orders.id],
                        userId = it[Orders.userId],
                        item = it[Orders.product],
                        quantity = it[Orders.quantity]
                    )
                )
            }
            UserOrderDTO(firstName, lastName, orderList)
        }
    }

    fun getAllOrders(): List<OrderDTO> {
        return databaseModule.dbTransaction {
            Orders.selectAll().map {
                OrderDTO(
                    id = it[Orders.id],
                    userId = it[Orders.userId],
                    item = it[Orders.product],
                    quantity = it[Orders.quantity]
                )
            }
        }
    }

    fun addOrder(order: OrderDTO) {
        databaseModule.dbTransaction {
            Orders.insert {
                it[userId] = order.userId
                it[product] = order.item
                it[quantity] = order.quantity
            }
        }
    }
}