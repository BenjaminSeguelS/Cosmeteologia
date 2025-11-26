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

data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ProductDetailViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun loadProduct(barcode: String) {
        _uiState.update { it.copy(isLoading = true) }
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

    class Factory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductDetailViewModel(productRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
