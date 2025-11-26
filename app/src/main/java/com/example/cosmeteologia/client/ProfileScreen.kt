package com.example.cosmeteologia.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cosmeteologia.ui.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    onBackClick: () -> Unit
) {
    val uiState by profileViewModel.profileUiState.collectAsState()
    val user = uiState.user
    val clientData = uiState.clientData

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (user != null) {
                Text("Datos de la Cuenta", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                ProfileInfoCard(label = "Correo Electrónico", value = user.email)
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (clientData != null) {
                Text("Datos del Formulario", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                ProfileInfoCard("Nombre", clientData.nombre)
                ProfileInfoCard("Contacto", clientData.contacto)
                ProfileInfoCard("Edad", clientData.edad)
                ProfileInfoCard("Consume Tabaco", if (clientData.consumeTabaco) "Sí" else "No")
                ProfileInfoCard("Consume Alcohol", if (clientData.consumeAlcohol) "Sí" else "No")
                ProfileInfoCard("Realiza Deporte", if (clientData.realizaDeporte) "Sí" else "No")
                ProfileInfoCard("Antecedentes Cardíacos", clientData.antecedentesCardiacos)
                ProfileInfoCard("Enfermedades", clientData.enfermedades)
                ProfileInfoCard("Alergias", clientData.alergias)
                ProfileInfoCard("Medicamentos", clientData.medicamentos)
                ProfileInfoCard("Productos que Utiliza", clientData.productosQueUtiliza)
                ProfileInfoCard("Cirugías Estéticas", clientData.cirugiasEsteticas)
                ProfileInfoCard("Implantes Metálicos", clientData.implantesMetalicos)
            }
        }
    }
}

@Composable
fun ProfileInfoCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = label, style = MaterialTheme.typography.bodySmall)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
