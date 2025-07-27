package com.sakhura.controlpaseosmascotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sakhura.controlpaseosmascotas.datos.BaseDeDatosPaseos
import com.sakhura.controlpaseosmascotas.datos.EntidadPaseoMascota
import com.sakhura.controlpaseosmascotas.modelovista.ModeloVistaPaseos
import com.sakhura.controlpaseosmascotas.repositorio.RepositorioPaseosMascotas
import com.sakhura.controlpaseosmascotas.ui.theme.ControlpaseosmascotasTheme
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControlpaseosmascotasTheme {
                AplicacionPaseosMascotas()
            }
        }
    }
}

// Pantalla principal de la aplicación
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AplicacionPaseosMascotas() {
    // Configurar la base de datos y el ViewModel
    val context = LocalContext.current
    val baseDeDatos = BaseDeDatosPaseos.obtenerBaseDeDatos(context)
    val repositorio = RepositorioPaseosMascotas(baseDeDatos.accesoDatosPaseos())
    val viewModel: ModeloVistaPaseos = viewModel { ModeloVistaPaseos(repositorio) }

    // Estado para mostrar/ocultar el formulario
    var mostrandoFormulario by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🐕 Control de Paseos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrandoFormulario = !mostrandoFormulario }
            ) {
                Icon(
                    imageVector = if (mostrandoFormulario) Icons.Default.Close else Icons.Default.Add,
                    contentDescription = if (mostrandoFormulario) "Cerrar" else "Agregar paseo"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Mostrar estadísticas de dinero
            EstadisticasCard(viewModel)

            Spacer(modifier = Modifier.height(16.dp))

            if (mostrandoFormulario) {
                // Mostrar formulario para agregar nuevo paseo
                FormularioNuevoPaseo(viewModel) {
                    mostrandoFormulario = false
                }
            } else {
                // Mostrar lista de todos los paseos
                ListaDePaseos(viewModel)
            }
        }
    }
}

// Tarjeta que muestra las estadísticas de dinero
@Composable
fun EstadisticasCard(viewModel: ModeloVistaPaseos) {
    val totalGanado by viewModel.totalGanado.collectAsState()
    val totalPendiente by viewModel.totalPendiente.collectAsState()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "📊 Estadísticas",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Dinero ya ganado (pagado)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "💰 Ganado",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = formatearDinero(totalGanado),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF4CAF50), // Verde
                        fontWeight = FontWeight.Bold
                    )
                }

                // Dinero pendiente de cobro
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "⏳ Pendiente",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = formatearDinero(totalPendiente),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFFFF9800), // Naranja
                        fontWeight = FontWeight.Bold
                    )
                }

                // Total general
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "💵 Total",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = formatearDinero(totalGanado + totalPendiente),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Formulario para agregar un nuevo paseo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioNuevoPaseo(
    viewModel: ModeloVistaPaseos,
    onPaseoAgregado: () -> Unit
) {
    // Obtener los estados del formulario del ViewModel
    val nombreMascota by viewModel.nombreMascota.collectAsState()
    val tipoMascota by viewModel.tipoMascota.collectAsState()
    val nombreCliente by viewModel.nombreCliente.collectAsState()
    val duracionHoras by viewModel.duracionHoras.collectAsState()
    val tarifaPorHora by viewModel.tarifaPorHora.collectAsState()
    val notas by viewModel.notas.collectAsState()

    // Estado para el dropdown de tipo de mascota
    var expandedTipoMascota by remember { mutableStateOf(false) }
    val tiposMascotas = listOf("Perro", "Gato", "Conejo", "Otro")

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "➕ Nuevo Paseo",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Nombre de la mascota
            OutlinedTextField(
                value = nombreMascota,
                onValueChange = viewModel::actualizarNombreMascota,
                label = { Text("🐕 Nombre de la mascota") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo: Tipo de mascota (Dropdown)
            ExposedDropdownMenuBox(
                expanded = expandedTipoMascota,
                onExpandedChange = { expandedTipoMascota = !expandedTipoMascota }
            ) {
                OutlinedTextField(
                    value = tipoMascota,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("🐾 Tipo de mascota") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipoMascota)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expandedTipoMascota,
                    onDismissRequest = { expandedTipoMascota = false }
                ) {
                    tiposMascotas.forEach { tipo ->
                        DropdownMenuItem(
                            text = { Text(tipo) },
                            onClick = {
                                viewModel.actualizarTipoMascota(tipo)
                                expandedTipoMascota = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo: Nombre del cliente
            OutlinedTextField(
                value = nombreCliente,
                onValueChange = viewModel::actualizarNombreCliente,
                label = { Text("👤 Nombre del cliente") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campos: Duración y Tarifa (en la misma fila)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Duración en horas
                OutlinedTextField(
                    value = duracionHoras,
                    onValueChange = viewModel::actualizarDuracionHoras,
                    label = { Text("⏱️ Horas") },
                    modifier = Modifier.weight(1f)
                )

                // Tarifa por hora
                OutlinedTextField(
                    value = tarifaPorHora,
                    onValueChange = viewModel::actualizarTarifaPorHora,
                    label = { Text("💵 Tarifa/hora") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar cálculo automático del total
            if (duracionHoras.isNotEmpty() && tarifaPorHora.isNotEmpty()) {
                val total = viewModel.calcularMontoTotal()
                Text(
                    text = "💰 Total: ${formatearDinero(total)}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            // Campo: Notas adicionales
            OutlinedTextField(
                value = notas,
                onValueChange = viewModel::actualizarNotas,
                label = { Text("📝 Notas (opcional)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar el paseo
            Button(
                onClick = {
                    viewModel.agregarPaseo()
                    onPaseoAgregado()
                },
                enabled = viewModel.formularioEsValido(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("💾 Guardar Paseo")
            }
        }
    }
}

// Lista de todos los paseos registrados
@Composable
fun ListaDePaseos(viewModel: ModeloVistaPaseos) {
    val paseos by viewModel.paseos.collectAsState()

    Text(
        text = "📋 Lista de Paseos",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(8.dp))

    if (paseos.isEmpty()) {
        // Mostrar mensaje cuando no hay paseos
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🐕",
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = "No hay paseos registrados",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "¡Agrega tu primer paseo con el botón +!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    } else {
        // Mostrar lista de paseos
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(paseos) { paseo ->
                TarjetaPaseo(
                    paseo = paseo,
                    onCambiarEstadoPago = { viewModel.cambiarEstadoPago(paseo) },
                    onEliminar = { viewModel.eliminarPaseo(paseo) }
                )
            }
        }
    }
}

// Tarjeta individual para cada paseo
@Composable
fun TarjetaPaseo(
    paseo: EntidadPaseoMascota,
    onCambiarEstadoPago: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (paseo.estaPagado) {
                Color(0xFFE8F5E8) // Verde claro para paseos pagados
            } else {
                Color(0xFFFFF3E0) // Naranja claro para paseos pendientes
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Nombre de la mascota con emoji según el tipo
                    Text(
                        text = "${obtenerEmojiTipo(paseo.tipoMascota)} ${paseo.nombreMascota}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    // Nombre del cliente
                    Text(
                        text = "👤 ${paseo.nombreCliente}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Fecha del paseo
                    Text(
                        text = "📅 ${formatearFecha(paseo.fecha)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Chip para mostrar/cambiar estado de pago
                AssistChip(
                    onClick = onCambiarEstadoPago,
                    label = {
                        Text(
                            text = if (paseo.estaPagado) "✅ Pagado" else "⏳ Pendiente"
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (paseo.estaPagado) {
                            Color(0xFF4CAF50) // Verde para pagado
                        } else {
                            Color(0xFFFF9800) // Naranja para pendiente
                        },
                        labelColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Información del paseo: duración, tarifa y total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "⏱️ ${paseo.duracionHoras}h",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${formatearDinero(paseo.tarifaPorHora)}/h",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "💰 ${formatearDinero(paseo.montoTotal)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Mostrar notas si las hay
            if (paseo.notas.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "📝 ${paseo.notas}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botón para eliminar el paseo
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onEliminar,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFFD32F2F) // Rojo para eliminar
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Eliminar")
                }
            }
        }
    }
}

// Funciones de utilidad para formatear datos

// Formatear dinero en pesos chilenos
fun formatearDinero(cantidad: Double): String {
    val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
    return formato.format(cantidad)
}

// Formatear fecha en formato dd/mm/yyyy
fun formatearFecha(fecha: Date): String {
    val formato = SimpleDateFormat("dd/MM/yyyy", Locale("es", "CL"))
    return formato.format(fecha)
}

// Obtener emoji según el tipo de mascota
fun obtenerEmojiTipo(tipo: String): String {
    return when (tipo) {
        "Perro" -> "🐕"
        "Gato" -> "🐱"
        "Conejo" -> "🐰"
        else -> "🐾"
    }
}