package com.example.cosmeteologia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cosmeteologia.data.ClientData
import com.example.cosmeteologia.data.ClientDataDao
import kotlinx.coroutines.flow.Flow

class FormViewModel(private val clientDataDao: ClientDataDao) : ViewModel() {

    val allClients: Flow<List<ClientData>> = clientDataDao.getAll()

    suspend fun submitForm(clientData: ClientData) {
        clientDataDao.insert(clientData)
    }

    class Factory(private val clientDataDao: ClientDataDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FormViewModel(clientDataDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
