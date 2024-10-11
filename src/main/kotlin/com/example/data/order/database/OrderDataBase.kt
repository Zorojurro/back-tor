package com.example.data.order.database

import com.example.data.user.database.Users
import org.jetbrains.exposed.sql.Table

object Orders : Table() {
    val id = integer("id").autoIncrement()
    val userId = integer("userId").references(Users.id)
    val product = varchar("product", 255)
    val quantity = integer("quantity")
    override val primaryKey = PrimaryKey(id)
}