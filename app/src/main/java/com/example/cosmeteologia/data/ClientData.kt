package com.example.cosmeteologia.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client_forms")
data class ClientData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String = "",
    val contacto: String = "",
    val correo: String = "",
    val edad: String = "",
    val consumeTabaco: Boolean = false,
    val consumeAlcohol: Boolean = false,
    val realizaDeporte: Boolean = false,
    val antecedentesCardiacos: String = "",
    val enfermedades: String = "",
    val alergias: String = "",
    val medicamentos: String = "",
    val productosQueUtiliza: String = "",
    val cirugiasEsteticas: String = "",
    val implantesMetalicos: String = "",
    val photoUri: String? = null
)
