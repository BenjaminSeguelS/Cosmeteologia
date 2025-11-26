package com.example.cosmeteologia.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cosmeteologia.auth.LoginScreen
import com.example.cosmeteologia.auth.RegisterScreen
import com.example.cosmeteologia.client.ClientDashboardScreen
import com.example.cosmeteologia.client.ClientScreen
import com.example.cosmeteologia.client.ProfileScreen
import com.example.cosmeteologia.client.scanner.ProductDetailScreen
import com.example.cosmeteologia.client.scanner.ProductScannerScreen
import com.example.cosmeteologia.data.AppDatabase
import com.example.cosmeteologia.data.UserType
import com.example.cosmeteologia.data.network.RetrofitInstance
import com.example.cosmeteologia.data.repository.ProductRepository
import com.example.cosmeteologia.professional.ProfessionalScreen
import com.example.cosmeteologia.ui.viewmodel.AuthViewModel
import com.example.cosmeteologia.ui.viewmodel.FormViewModel
import com.example.cosmeteologia.ui.viewmodel.ProductDetailViewModel
import com.example.cosmeteologia.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Client : Screen("client")
    object Professional : Screen("professional")
    object ClientDashboard : Screen("client_dashboard")
    object Profile : Screen("profile")
    object ProductScanner : Screen("product_scanner")
    object ProductDetail : Screen("product_detail/{barcode}") {
        fun createRoute(barcode: String) = "product_detail/$barcode"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(db.userDao()))
    val formViewModel: FormViewModel = viewModel(factory = FormViewModel.Factory(db.clientDataDao()))
    val profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory(authViewModel, db.clientDataDao()))

    val productRepository = remember { ProductRepository(RetrofitInstance.api) }
    val productDetailViewModel: ProductDetailViewModel = viewModel(factory = ProductDetailViewModel.Factory(productRepository))

    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { email, password, userType ->
                    scope.launch {
                        val user = authViewModel.login(email, password, userType)
                        if (user != null) {
                            when (user.userType) {
                                UserType.CLIENT -> navController.navigate(Screen.Client.route)
                                UserType.PROFESSIONAL -> navController.navigate(Screen.Professional.route)
                            }
                        } else {
                            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                onRegisterClick = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegister = {
                    scope.launch {
                        authViewModel.register(it)
                        navController.popBackStack()
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Client.route) {
            ClientScreen(
                onFormSubmit = {
                    scope.launch {
                        formViewModel.submitForm(it)
                        navController.navigate(Screen.ClientDashboard.route)
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Professional.route) {
            val clients by formViewModel.allClients.collectAsState(initial = emptyList())
            ProfessionalScreen(
                clients = clients,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.ClientDashboard.route) {
            ClientDashboardScreen(
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onProductsClick = { navController.navigate(Screen.ProductScanner.route) }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.ProductScanner.route) {
            ProductScannerScreen { barcode ->
                navController.navigate(Screen.ProductDetail.createRoute(barcode))
            }
        }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("barcode") { type = NavType.StringType })
        ) { backStackEntry ->
            val barcode = backStackEntry.arguments?.getString("barcode") ?: ""
            val uiState by productDetailViewModel.uiState.collectAsState()

            LaunchedEffect(barcode) {
                productDetailViewModel.loadProduct(barcode)
            }

            ProductDetailScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
