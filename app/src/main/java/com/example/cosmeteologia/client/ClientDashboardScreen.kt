package com.example.cosmeteologia.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientDashboardScreen(
    onProfileClick: () -> Unit,
    onProductsClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Panel de Cliente") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¡Bienvenido!")
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onProfileClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Revisar mi Perfil")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onProductsClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Productos")
            }
        }
    }
}