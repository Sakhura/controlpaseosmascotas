package com.sakhura.controlpaseosmascotas.datos

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import android.content.Context
import java.util.Date

// Clase para convertir fechas a números (para guardar en la base de datos)
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

// Configuración principal de nuestra base de datos
@Database(
    entities = [EntidadPaseoMascota::class], // Las tablas que tenemos
    version = 1, // Versión de la base de datos
    exportSchema = false // No exportar esquema por ahora
)
@TypeConverters(ConvertidoresDeTipo::class) // Usar nuestro convertidor de fechas
abstract class BaseDeDatosPaseos : RoomDatabase() {

    // Función para acceder a nuestro DAO
    abstract fun accesoDatosPaseos(): AccesoDatosPaseos

    companion object {
        @Volatile
        private var INSTANCIA: BaseDeDatosPaseos? = null

        // Función para obtener la base de datos (patrón Singleton)
        // Esto significa que solo habrá una instancia de la base de datos
        fun obtenerBaseDeDatos(context: Context): BaseDeDatosPaseos {
            // Si ya existe la instancia, la devolvemos
            return INSTANCIA ?: synchronized(this) {
                // Si no existe, la creamos
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatosPaseos::class.java,
                    "base_datos_paseos" // Nombre del archivo de la base de datos
                ).build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}