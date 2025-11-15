package com.example.cosmeteologia.auth

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.cosmeteologia.data.User
import com.example.cosmeteologia.data.UserType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegister: (User) -> Unit, onBackClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var isPasswordError by remember { mutableStateOf(false) }
    var userType by remember { mutableStateOf(UserType.CLIENT) }

    fun validate(): Boolean {
        isEmailError = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordError = password.isBlank() || password.length < 6
        return !isEmailError && !isPasswordError
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it; isEmailError = false },
                label = { Text("Correo electrónico") },
                isError = isEmailError,
                supportingText = { if (isEmailError) Text("Introduce un correo válido") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it; isPasswordError = false },
                label = { Text("Contraseña") },
                isError = isPasswordError,
                supportingText = { if (isPasswordError) Text("La contraseña debe tener al menos 6 caracteres") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Row(modifier = Modifier.padding(top = 16.dp)) {
                RadioButton(
                    selected = userType == UserType.CLIENT,
                    onClick = { userType = UserType.CLIENT }
                )
                Text("Cliente", modifier = Modifier.align(Alignment.CenterVertically))
                RadioButton(
                    selected = userType == UserType.PROFESSIONAL,
                    onClick = { userType = UserType.PROFESSIONAL }
                )
                Text("Profesional", modifier = Modifier.align(Alignment.CenterVertically))
            }

            Button(
                onClick = {
                    if (validate()) {
                        val user = User(email, password, userType)
                        onRegister(user)
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Registrarse")
            }
        }
    }
}
