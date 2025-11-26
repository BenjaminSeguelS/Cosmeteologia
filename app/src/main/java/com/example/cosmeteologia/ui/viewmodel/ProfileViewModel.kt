package com.example.cosmeteologia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cosmeteologia.data.ClientData
import com.example.cosmeteologia.data.ClientDataDao
import com.example.cosmeteologia.data.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    authViewModel: AuthViewModel,
    clientDataDao: ClientDataDao
) : ViewModel() {

    val profileUiState: StateFlow<ProfileUiState> = authViewModel.currentUser.flatMapLatest { user ->
        clientDataDao.getClientDataByEmailFlow(user?.email ?: "").combine(authViewModel.currentUser) { clientData, currentUser ->
            ProfileUiState(currentUser, clientData)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProfileUiState()
    )

    data class ProfileUiState(
        val user: User? = null,
        val clientData: ClientData? = null
    )

    class Factory(
        private val authViewModel: AuthViewModel,
        private val clientDataDao: ClientDataDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(authViewModel, clientDataDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
