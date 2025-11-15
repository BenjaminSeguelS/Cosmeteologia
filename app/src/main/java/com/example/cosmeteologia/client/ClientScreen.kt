package com.example.cosmeteologia.client

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cosmeteologia.data.ClientData
import com.example.cosmeteologia.form.FormScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(onFormSubmit: (ClientData) -> Unit, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario de Cliente") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        FormScreen(
            modifier = Modifier.padding(padding),
            onFormSubmit = onFormSubmit
        )
    }
}
