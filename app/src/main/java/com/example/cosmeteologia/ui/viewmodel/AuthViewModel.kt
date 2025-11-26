package com.example.cosmeteologia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cosmeteologia.data.User
import com.example.cosmeteologia.data.UserDao
import com.example.cosmeteologia.data.UserType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(private val userDao: UserDao) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    suspend fun login(email: String, password: String, userType: UserType): User? {
        val user = userDao.getUser(email)
        if (user != null && user.password == password && user.userType == userType) {
            _currentUser.value = user
            return user
        }
        return null
    }

    suspend fun register(user: User) {
        userDao.insert(user)
    }

    fun logout() {
        _currentUser.value = null
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
