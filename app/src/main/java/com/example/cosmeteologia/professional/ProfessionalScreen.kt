package com.example.cosmeteologia.professional

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.cosmeteologia.data.ClientData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessionalScreen(clients: List<ClientData>, onBackClick: () -> Unit) {
    var selectedClient by remember { mutableStateOf<ClientData?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clientes") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(clients) { client ->
                Button(
                    onClick = { selectedClient = client },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(client.nombre)
                }
            }
        }

        if (selectedClient != null) {
            ClientDataDialog(client = selectedClient!!) {
                selectedClient = null
            }
        }
    }
}

@Composable
fun ClientDataDialog(client: ClientData, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                client.photoUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Foto del cliente",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                Text("Nombre: ${client.nombre}", fontWeight = FontWeight.Bold)
                Text("Contacto: ${client.contacto}")
                Text("Correo: ${client.correo}")
                Text("Edad: ${client.edad}")

                Text("Estilo de vida", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                Text("Consume Tabaco: ${if (client.consumeTabaco) "Sí" else "No"}")
                Text("Consume Alcohol: ${if (client.consumeAlcohol) "Sí" else "No"}")
                Text("Realiza Deporte: ${if (client.realizaDeporte) "Sí" else "No"}")

                Text("Historial Médico", fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
                Text("Antecedentes Cardíacos: ${client.antecedentesCardiacos}")
                Text("Enfermedades: ${client.enfermedades}")
                Text("Alergias: ${client.alergias}")
                Text("Medicamentos: ${client.medicamentos}")
                Text("Productos que utiliza: ${client.productosQueUtiliza}")
                Text("Cirugías estéticas: ${client.cirugiasEsteticas}")
                Text("Implantes metálicos: ${client.implantesMetalicos}")

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}
