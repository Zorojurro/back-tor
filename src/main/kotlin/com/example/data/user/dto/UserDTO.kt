package com.example.data.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(val firstName: String? = null , val lastName: String? = null)