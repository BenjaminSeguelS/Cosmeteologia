package com.example.cosmeteologia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cosmeteologia.data.User
import com.example.cosmeteologia.data.UserDao
import com.example.cosmeteologia.data.UserType

class AuthViewModel(private val userDao: UserDao) : ViewModel() {

    suspend fun login(email: String, password: String, userType: UserType): User? {
        val user = userDao.getUser(email)
        return if (user != null && user.password == password && user.userType == userType) {
            user
        } else {
            null
        }
    }

    suspend fun register(user: User) {
        userDao.insert(user)
    }

    class Factory(private val userDao: UserDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(userDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
