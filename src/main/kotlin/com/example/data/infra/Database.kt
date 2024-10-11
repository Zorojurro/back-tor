package com.example.data.infra

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseModule @Inject constructor() {
    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/testdb",
            driver = "org.postgresql.Driver",
            user = "user",
            password = "password"
        )
    }

    fun <T> dbTransaction(block: () -> T): T {
        return transaction { block() }
    }
}