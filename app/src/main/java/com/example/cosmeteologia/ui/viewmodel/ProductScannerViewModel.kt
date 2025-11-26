package com.example.cosmeteologia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cosmeteologia.data.network.Product
import com.example.cosmeteologia.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductScannerUiState(
    val barcode: String? = null,
    val product: Product? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)

class ProductScannerViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductScannerUiState())
    val uiState: StateFlow<ProductScannerUiState> = _uiState.asStateFlow()

    fun onBarcodeScanned(barcode: String) {
        _uiState.update { it.copy(isLoading = true, barcode = barcode) }
        viewModelScope.launch {
            try {
                val productResponse = productRepository.getProduct(barcode)
                if (productResponse.product != null) {
                    _uiState.update { it.copy(product = productResponse.product, isLoading = false) }
                } else {
                    _uiState.update { it.copy(error = "Producto no encontrado", isLoading = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Error: ${e.message}", isLoading = false) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    class Factory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductScannerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductScannerViewModel(productRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
