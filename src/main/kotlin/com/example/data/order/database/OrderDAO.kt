package com.example.data.order.database

import com.example.data.infra.DatabaseModule
import com.example.data.order.dto.OrderDTO
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
        return databaseModule.dbTransaction{
            Orders.select { Orders.userId eq userId }
                .map {
                    OrderDTO(
                        id = it[Orders.id],
                        userId = it[Orders.userId],
                        item =  it[Orders.product],
                        quantity = it[Orders.quantity]
                    )
                }
        }
    }

    fun getAllOrders(): List<OrderDTO> {
        return databaseModule.dbTransaction {
            Orders.selectAll().map {
                OrderDTO(
                    id = it[Orders.id],
                    userId = it[Orders.userId],
                    item =  it[Orders.product],
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