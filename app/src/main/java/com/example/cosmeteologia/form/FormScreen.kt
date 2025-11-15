package com.example.cosmeteologia.form

import android.Manifest
import android.net.Uri
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cosmeteologia.data.ClientData

@Composable
fun FormScreen(
    onFormSubmit: (ClientData) -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by remember { mutableStateOf("") }
    var isNombreError by remember { mutableStateOf(false) }
    var contacto by remember { mutableStateOf("") }
    var isContactoError by remember { mutableStateOf(false) }
    var correo by remember { mutableStateOf("") }
    var isCorreoError by remember { mutableStateOf(false) }
    var edad by remember { mutableStateOf("") }
    var isEdadError by remember { mutableStateOf(false) }

    var consumeTabaco by remember { mutableStateOf(false) }
    var consumeAlcohol by remember { mutableStateOf(false) }
    var realizaDeporte by remember { mutableStateOf(false) }
    var antecedentesCardiacos by remember { mutableStateOf("") }
    var enfermedades by remember { mutableStateOf("") }
    var alergias by remember { mutableStateOf("") }
    var medicamentos by remember { mutableStateOf("") }
    var productosQueUtiliza by remember { mutableStateOf("") }
    var cirugiasEsteticas by remember { mutableStateOf("") }
    var implantesMetalicos by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        // Aquí deberías guardar el bitmap en el almacenamiento y obtener la Uri
        // Por ahora, solo lo mostramos en la UI
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(null)
        }
    }

    fun validate(): Boolean {
        isNombreError = nombre.isBlank()
        isContactoError = contacto.isBlank() || !contacto.all { it.isDigit() }
        isCorreoError = correo.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()
        isEdadError = edad.isBlank() || !edad.all { it.isDigit() }

        return !isNombreError && !isContactoError && !isCorreoError && !isEdadError
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it; isNombreError = false },
            label = { Text("Nombre") },
            isError = isNombreError,
            supportingText = { if (isNombreError) Text("El nombre es obligatorio") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = contacto,
            onValueChange = { contacto = it; isContactoError = false },
            label = { Text("Contacto") },
            isError = isContactoError,
            supportingText = { if (isContactoError) Text("El contacto es obligatorio y solo debe contener números") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it; isCorreoError = false },
            label = { Text("Correo") },
            isError = isCorreoError,
            supportingText = { if (isCorreoError) Text("Introduce un correo válido") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it; isEdadError = false },
            label = { Text("Edad") },
            isError = isEdadError,
            supportingText = { if (isEdadError) Text("La edad es obligatoria y solo debe contener números") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Estilo de vida", modifier = Modifier.padding(top = 16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = consumeTabaco, onCheckedChange = { consumeTabaco = it })
            Text("Consume Tabaco")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = consumeAlcohol, onCheckedChange = { consumeAlcohol = it })
            Text("Consume Alcohol")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = realizaDeporte, onCheckedChange = { realizaDeporte = it })
            Text("Realiza Deporte")
        }

        OutlinedTextField(value = antecedentesCardiacos, onValueChange = { antecedentesCardiacos = it }, label = { Text("Antecedentes Cardiacos") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = enfermedades, onValueChange = { enfermedades = it }, label = { Text("Enfermedades (diabetes, cáncer, etc..)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = alergias, onValueChange = { alergias = it }, label = { Text("Alergias") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = medicamentos, onValueChange = { medicamentos = it }, label = { Text("Medicamentos") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = productosQueUtiliza, onValueChange = { productosQueUtiliza = it }, label = { Text("Productos que utiliza") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = cirugiasEsteticas, onValueChange = { cirugiasEsteticas = it }, label = { Text("Cirugías estéticas") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = implantesMetalicos, onValueChange = { implantesMetalicos = it }, label = { Text("Implantes metálicos") }, modifier = Modifier.fillMaxWidth())
        
        Button(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
            Text("Tomar Foto")
        }

        photoUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Foto del cliente",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        Button(
            onClick = {
                if (validate()) {
                    val clientData = ClientData(
                        nombre = nombre,
                        contacto = contacto,
                        correo = correo,
                        edad = edad,
                        consumeTabaco = consumeTabaco,
                        consumeAlcohol = consumeAlcohol,
                        realizaDeporte = realizaDeporte,
                        antecedentesCardiacos = antecedentesCardiacos,
                        enfermedades = enfermedades,
                        alergias = alergias,
                        medicamentos = medicamentos,
                        productosQueUtiliza = productosQueUtiliza,
                        cirugiasEsteticas = cirugiasEsteticas,
                        implantesMetalicos = implantesMetalicos,
                        photoUri = photoUri?.toString()
                    )
                    onFormSubmit(clientData)
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar")
        }
    }
}
