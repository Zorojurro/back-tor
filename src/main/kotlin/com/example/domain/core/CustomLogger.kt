package com.example.domain.core

import org.jetbrains.exposed.sql.exposedLogger

interface Logger {
    fun info(message: String)
    fun warning(message: String)
    fun error(message: String)
}

class ConsoleLogger : Logger {
    private companion object {
        const val ANSI_RESET = "\u001B[0m"
        const val ANSI_RED = "\u001B[31m"
        const val ANSI_YELLOW = "\u001B[33m"
        const val ANSI_GREEN = "\u001B[32m"
    }

    override fun info(message: String) {
        exposedLogger.info("${ANSI_GREEN}$message${ANSI_RESET}")
    }

    override fun warning(message: String) {
        exposedLogger.warn("${ANSI_YELLOW}$message${ANSI_RESET}")
    }

    override fun error(message: String) {
        exposedLogger.error("${ANSI_RED}$message${ANSI_RESET}")
    }
}
