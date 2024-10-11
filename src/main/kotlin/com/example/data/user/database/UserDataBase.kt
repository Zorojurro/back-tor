package com.example.data.user.database

import com.example.data.infra.DatabaseModule
import com.example.data.user.dto.UserDTO
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject


object Users : Table() {
    val id = integer("id").autoIncrement()
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)

    override val primaryKey = PrimaryKey(id)
}


class UserDao @Inject constructor(private val databaseModule: DatabaseModule) {

    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    fun createUser(firstName: String, lastName: String): UserDTO {
        return databaseModule.dbTransaction {
            Users.insert {
                it[Users.firstName] = firstName
                it[Users.lastName] = lastName
            }
            UserDTO(firstName, lastName)
        }
    }

    fun getAllUser(): List<UserDTO> {
        return databaseModule.dbTransaction {
            Users.selectAll().map {
                UserDTO(it[Users.firstName], it[Users.lastName])
            }
        }
    }

    fun findUserByLastName(lastName: String): List<UserDTO> {
        return databaseModule.dbTransaction {
            Users.select { Users.lastName eq lastName }
                .mapNotNull { row ->
                    UserDTO(row[Users.firstName], row[Users.lastName])
                }
        }
    }
}