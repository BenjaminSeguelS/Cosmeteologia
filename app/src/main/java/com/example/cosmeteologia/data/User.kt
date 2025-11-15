package com.example.cosmeteologia.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserType {
    CLIENT,
    PROFESSIONAL
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val email: String,
    val password: String,
    val userType: UserType
)
