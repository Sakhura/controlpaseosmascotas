package com.sakhura.controlpaseosmascotas.datos

import androidx.room.TypeConverter
import java.util.Date

// Clase para convertir fechas a números (para guardar en la base de datos)
// Room no sabe cómo guardar fechas directamente, así que las convertimos a números
class ConvertidoresDeTipo {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}