package com.example.cosmeteologia

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cosmeteologia.client.scanner.ProductDetailScreen
import com.example.cosmeteologia.data.network.Product
import com.example.cosmeteologia.ui.theme.CosmeteologiaTheme
import com.example.cosmeteologia.ui.viewmodel.ProductDetailUiState
import org.junit.Rule
import org.junit.Test

class ProductDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productDetails_whenStateIsSuccess_areDisplayed() {
        // 1. Arrange: Create a fake product and UI state
        val fakeProduct = Product(
            productName = "Crema Hidratante de Test",
            imageUrl = "",
            brands = "Marca de Prueba",
            ingredientsText = "Agua, Glicerina, Ingredientes de prueba"
        )
        val fakeUiState = ProductDetailUiState(
            product = fakeProduct,
            isLoading = false,
            error = null
        )

        // 2. Act: Render the screen with the fake state
        composeTestRule.setContent {
            CosmeteologiaTheme {
                ProductDetailScreen(
                    uiState = fakeUiState,
                    onBackClick = {}
                )
            }
        }

        // 3. Assert: Verify that the product data is displayed
        composeTestRule.onNodeWithText("Crema Hidratante de Test").assertIsDisplayed()
        composeTestRule.onNodeWithText("Marca: Marca de Prueba").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ingredientes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Agua, Glicerina, Ingredientes de prueba").assertIsDisplayed()
    }
}
