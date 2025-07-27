package com.sakhura.controlpaseosmascotas.datos

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// DAO = Data Access Object
// Aquí definimos cómo vamos a acceder a los datos de la base de datos
@Dao
interface AccesoDatosPaseos {

    // Obtener todos los paseos ordenados por fecha (más recientes primero)
    @Query("SELECT * FROM paseos_mascotas ORDER BY fecha DESC")
    fun obtenerTodosLosPaseos(): Flow<List<EntidadPaseoMascota>>

    // Obtener solo los paseos que no han sido pagados
    @Query("SELECT * FROM paseos_mascotas WHERE estaPagado = 0 ORDER BY fecha DESC")
    fun obtenerPaseosPendientes(): Flow<List<EntidadPaseoMascota>>

    // Obtener solo los paseos que ya fueron pagados
    @Query("SELECT * FROM paseos_mascotas WHERE estaPagado = 1 ORDER BY fecha DESC")
    fun obtenerPaseosPagados(): Flow<List<EntidadPaseoMascota>>

    // Agregar un nuevo paseo a la base de datos
    @Insert
    suspend fun insertarPaseo(paseo: EntidadPaseoMascota)

    // Actualizar un paseo existente (por ejemplo, marcarlo como pagado)
    @Update
    suspend fun actualizarPaseo(paseo: EntidadPaseoMascota)

    // Eliminar un paseo de la base de datos
    @Delete
    suspend fun eliminarPaseo(paseo: EntidadPaseoMascota)

    // Calcular total de dinero ganado (solo paseos que ya fueron pagados)
    @Query("SELECT SUM(montoTotal) FROM paseos_mascotas WHERE estaPagado = 1")
    fun obtenerTotalGanado(): Flow<Double?>

    // Calcular dinero pendiente de cobro (paseos no pagados)
    @Query("SELECT SUM(montoTotal) FROM paseos_mascotas WHERE estaPagado = 0")
    fun obtenerTotalPendiente(): Flow<Double?>
}