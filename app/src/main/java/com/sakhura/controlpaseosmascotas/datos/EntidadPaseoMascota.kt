package com.sakhura.controlpaseosmascotas.datos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Esta es nuestra tabla en la base de datos
// Cada paseo que registremos será una fila en esta tabla
@Entity(tableName = "paseos_mascotas")
data class EntidadPaseoMascota(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // ID único para cada paseo

    val nombreMascota: String, // Nombre de la mascota (ej: "Firulais")
    val tipoMascota: String, // Tipo: Perro, Gato, Conejo, Otro
    val nombreCliente: String, // Nombre del dueño
    val duracionHoras: Double, // Cuántas horas duró el paseo
    val tarifaPorHora: Double, // Cuánto cobramos por hora
    val montoTotal: Double, // Total a cobrar (horas × tarifa)
    val estaPagado: Boolean, // ¿Ya nos pagaron? true/false
    val fecha: Date, // Cuándo fue el paseo
    val notas: String = "" // Comentarios extra
)