package com.example.domain.users.repository


import com.example.data.user.dto.UserDTO

interface UsersRepository {
    suspend fun getAllUsers() : List<UserDTO>
    suspend fun addUser(firstName : String, lastName : String)
    suspend fun findUserByLastName(lastName : String) : List<UserDTO>
}