package com.example.domain.users.usecase

import com.example.data.user.dto.UserDTO
import com.example.domain.users.repository.UsersRepository
import javax.inject.Inject


class UsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun getAllUser() : List<UserDTO> = usersRepository.getAllUsers()
    suspend fun addUser(name: String, lastName: String) {
        usersRepository.addUser(name, lastName)
    }
    suspend fun getUserByLastName(lastName: String) : List<UserDTO> {
        return usersRepository.findUserByLastName(lastName)
    }
}