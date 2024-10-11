package com.example.data.user.repository

import com.example.data.user.database.UserDao
import com.example.data.user.dto.UserDTO
import com.example.domain.users.repository.UsersRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UsersRepository {
    override suspend fun getAllUsers(): List<UserDTO> {
        return userDao.getAllUser()
    }

    override suspend fun addUser(firstName: String, lastName: String) {
       userDao.createUser(firstName, lastName)
    }

    override suspend fun findUserByLastName(lastName: String): List<UserDTO> {
       return userDao.findUserByLastName(lastName)
    }
}