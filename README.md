# 🐕 Control de Paseos de Mascotas

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Room](https://img.shields.io/badge/Room-FF6B35?style=for-the-badge&logo=sqlite&logoColor=white)

**Una aplicación Android moderna para gestionar un negocio de paseos de mascotas**

*Desarrollado con las mejores prácticas de la industria y arquitectura MVVM*

[📱 Capturas](#-capturas-de-pantalla) • [🚀 Funcionalidades](#-funcionalidades) • [🏗️ Arquitectura](#️-arquitectura) • [📋 Instalación](#-instalación)

</div>

---

## 📖 Descripción

**Control de Paseos de Mascotas** es una aplicación móvil completa desarrollada en **Kotlin** con **Jetpack Compose** que permite gestionar eficientemente un negocio de cuidado de mascotas. La aplicación implementa patrones de arquitectura modernos y mejores prácticas de desarrollo Android.

### 🎯 Problema que Resuelve

Los cuidadores de mascotas necesitan una herramienta digital para:
- ✅ Registrar cada paseo realizado
- 💰 Calcular automáticamente los montos a cobrar
- 📊 Llevar control de ganancias y pagos pendientes
- 🐾 Organizar información de clientes y mascotas
- 📱 Acceder a la información desde cualquier lugar

---

## 🚀 Funcionalidades

### 🏠 **Pantalla Principal**
- 📊 **Dashboard financiero** con estadísticas en tiempo real
- 💰 **Dinero ganado** vs **dinero pendiente** vs **total general**
- 📈 **Indicadores visuales** con código de colores
- ➕ **Acceso rápido** para agregar nuevos paseos

### 📝 **Registro de Paseos**
- 🐕 **Información de mascota**: Nombre y tipo (Perro, Gato, Conejo, Otro)
- 👤 **Datos del cliente**: Nombre del propietario
- ⏱️ **Duración del servicio**: Control de horas trabajadas
- 💵 **Tarifa personalizada**: Precio por hora configurable
- 🧮 **Cálculo automático**: Total = Horas × Tarifa
- 📝 **Notas adicionales**: Observaciones del paseo
- 📅 **Timestamp automático**: Fecha y hora del registro

### 💳 **Gestión de Pagos**
- ✅ **Estados de pago**: Pendiente / Pagado
- 🎨 **Indicadores visuales**: Colores diferenciados por estado
- 💰 **Cálculos en tiempo real**: Totales actualizados automáticamente
- 🔄 **Cambio de estado**: Un toque para marcar como pagado

### 📊 **Sistema de Reportes**
- 📈 **Estadísticas financieras**: Ingresos totales y pendientes
- 📋 **Historial completo**: Lista ordenada por fecha
- 🔍 **Información detallada**: Todos los datos en tarjetas organizadas
- 📱 **Interfaz responsive**: Adaptada a diferentes tamaños de pantalla

### 🗑️ **Gestión de Datos**
- ➕ **Crear**: Nuevos registros de paseos
- 📖 **Leer**: Visualización de todos los datos
- ✏️ **Actualizar**: Cambiar estado de pagos
- 🗑️ **Eliminar**: Remover registros incorrectos

---

## 🏗️ Arquitectura

### 📐 **Patrón MVVM (Model-View-ViewModel)**

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      VIEW       │    │    VIEWMODEL    │    │      MODEL      │
│  (Jetpack       │◄──►│   (Business     │◄──►│   (Data Layer)  │
│   Compose)      │    │     Logic)      │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### 🗂️ **Estructura del Proyecto**

```
com.sakhura.controlpaseosmascotas/
├── 📱 MainActivity.kt                    # Actividad principal
├── 💾 datos/                            # Capa de datos
│   ├── EntidadPaseoMascota.kt           # Modelo de datos
│   ├── AccesoDatosPaseos.kt             # DAO (Data Access Object)
│   ├── ConvertidoresDeTipo.kt           # Type converters
│   └── BaseDeDatosPaseos.kt             # Configuración de Room
├── 🔄 repositorio/                      # Capa de abstracción
│   └── RepositorioPaseosMascotas.kt     # Repository pattern
├── 🧠 modelovista/                      # Lógica de negocio
│   └── ModeloVistaPaseos.kt             # ViewModel
└── 🎨 ui/theme/                         # Tema visual
    ├── Color.kt                         # Paleta de colores
    ├── Theme.kt                         # Configuración del tema
    └── Type.kt                          # Tipografía
```

### 🔧 **Tecnologías Utilizadas**

| Componente | Tecnología | Propósito |
|------------|------------|-----------|
| **UI Framework** | Jetpack Compose | Interfaz de usuario moderna y declarativa |
| **Lenguaje** | Kotlin | Lenguaje principal de desarrollo |
| **Base de Datos** | Room (SQLite) | Persistencia local de datos |
| **Arquitectura** | MVVM + Repository | Separación de responsabilidades |
| **Concurrencia** | Coroutines + Flow | Programación asíncrona y reactiva |
| **Design System** | Material Design 3 | Componentes y guías de diseño |
| **Inyección de Dependencias** | Manual | Configuración simple y directa |

---

## 📱 Capturas de Pantalla
<img width="387" height="627" alt="image" src="https://github.com/user-attachments/assets/d84fa109-74b5-4509-8658-11e9d8afe190" />
<img width="374" height="609" alt="image" src="https://github.com/user-attachments/assets/27a2f4bf-3945-4d00-9c58-f7df02c40b7c" />
<img width="376" height="590" alt="image" src="https://github.com/user-attachments/assets/856361ad-db31-490f-8dfd-6bbbc24eb8c5" />



### 🏠 Pantalla Principal
```
┌─────────────────────────────────┐
│ 🐕 Control de Paseos            │
├─────────────────────────────────┤
│ 📊 Estadísticas                 │
│ ┌─────────┬─────────┬─────────┐ │
│ │💰 Ganado│⏳ Pendien│💵 Total │ │
│ │ $50.000 │ $25.000 │ $75.000 │ │
│ └─────────┴─────────┴─────────┘ │
├─────────────────────────────────┤
│ 📋 Lista de Paseos              │
│ ┌─────────────────────────────┐ │
│ │🐕 Firulais    [✅ Pagado]  │ │
│ │👤 María González            │ │
│ │📅 27/07/2025                │ │
│ │⏱️ 2h 💵$5.000/h 💰$10.000  │ │
│ └─────────────────────────────┘ │
└─────────────────────────────────┘
```

### ➕ Formulario de Registro
```
┌─────────────────────────────────┐
│ ➕ Nuevo Paseo                  │
├─────────────────────────────────┤
│ 🐕 Nombre: [Firulais        ] │
│ 🐾 Tipo:   [Perro         ▼] │
│ 👤 Cliente: [María González  ] │
│ ⏱️ Horas: [2] 💵 Tarifa: [5000] │
│ ┌─────────────────────────────┐ │
│ │💰 Total: $10.000            │ │
│ └─────────────────────────────┘ │
│ 📝 Notas: [Muy juguetón     ] │
│ ┌─────────────────────────────┐ │
│ │    💾 Guardar Paseo         │ │
│ └─────────────────────────────┘ │
└─────────────────────────────────┘
```

---

## 📋 Instalación

### 📋 **Prerrequisitos**

- ✅ Android Studio Arctic Fox o superior
- ✅ JDK 11 o superior
- ✅ Android SDK API 24 (Android 7.0) o superior
- ✅ Dispositivo Android o emulador para pruebas

### 🛠️ **Pasos de Instalación**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/usuario/control-paseos-mascotas.git
   cd control-paseos-mascotas
   ```

2. **Abrir en Android Studio**
   - File → Open
   - Seleccionar la carpeta del proyecto
   - Esperar a que Gradle sincronice

3. **Configurar el proyecto**
   ```bash
   # Sync automático al abrir, o manualmente:
   ./gradlew build
   ```

4. **Ejecutar la aplicación**
   - Seleccionar dispositivo/emulador
   - Click en Run (▶️) o `Shift + F10`

### 📦 **Dependencias Principales**

```kotlin
dependencies {
    // Jetpack Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    
    // Compose Core
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.10.1")
    
    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
    
    // Icons
    implementation("androidx.compose.material:material-icons-extended")
}
```

---

## 🎯 Uso de la Aplicación

### 🚀 **Primer Uso**

1. **Abrir la aplicación**
   - Al iniciar, verás las estadísticas en $0
   - Mensaje de bienvenida para agregar el primer paseo

2. **Registrar primer paseo**
   - Toca el botón flotante `+`
   - Completa el formulario:
     - Nombre de la mascota
     - Tipo de mascota (dropdown)
     - Nombre del cliente
     - Duración en horas
     - Tarifa por hora
     - Notas opcionales
   - El total se calcula automáticamente
   - Toca "Guardar Paseo"

3. **Gestionar pagos**
   - En la lista, toca el chip "Pendiente"
   - Cambiará a "Pagado" y actualizará las estadísticas
   - Los colores cambian para indicar el estado

### 💡 **Tips de Uso**

- **📊 Monitoreo financiero**: Revisa regularmente las estadísticas para conocer tus ingresos
- **🔄 Actualización rápida**: Un toque en el estado cambia pendiente ↔ pagado
- **📝 Notas útiles**: Agrega observaciones sobre el comportamiento de las mascotas
- **🗑️ Limpieza de datos**: Elimina registros incorrectos usando el botón "Eliminar"
- **💰 Cálculo automático**: Cambia horas o tarifa y ve el total actualizarse al instante

---

## 🧪 Testing

### 🔬 **Casos de Prueba**

| Funcionalidad | Caso de Prueba | Resultado Esperado |
|---------------|----------------|-------------------|
| **Agregar Paseo** | Completar formulario válido | ✅ Paseo guardado y visible en lista |
| **Validación** | Enviar formulario incompleto | ❌ Botón deshabilitado |
| **Cálculo** | Cambiar horas/tarifa | 🧮 Total actualizado automáticamente |
| **Estado de Pago** | Tocar chip "Pendiente" | 🔄 Cambia a "Pagado" y actualiza estadísticas |
| **Eliminación** | Tocar "Eliminar" | 🗑️ Paseo removido de lista y estadísticas |
| **Persistencia** | Cerrar y abrir app | 💾 Datos mantenidos correctamente |

### 🧪 **Ejecutar Tests**

```bash
# Tests unitarios
./gradlew test

# Tests de instrumentación
./gradlew connectedAndroidTest

# Reporte de cobertura
./gradlew jacocoTestReport
```

---

## 🎓 Aspectos Educativos

### 📚 **Conceptos Aprendidos**

Este proyecto es ideal para estudiantes de programación ya que implementa:

#### **🔥 Kotlin Avanzado**
- ✅ Data classes con parámetros por defecto
- ✅ Extension functions para formateo
- ✅ Scope functions (let, apply, also)
- ✅ Coroutines y Flow para programación reactiva
- ✅ Null safety y smart casting

#### **📱 Desarrollo Android Moderno**
- ✅ Jetpack Compose (UI declarativa)
- ✅ Material Design 3 components
- ✅ State management y recomposition
- ✅ Navigation patterns
- ✅ Lifecycle awareness

#### **🏗️ Arquitectura de Software**
- ✅ MVVM (Model-View-ViewModel)
- ✅ Repository Pattern
- ✅ Dependency Injection manual
- ✅ Separation of Concerns
- ✅ Clean Architecture principles

#### **💾 Persistencia de Datos**
- ✅ Room Database (ORM)
- ✅ Entity relationships
- ✅ Type converters
- ✅ CRUD operations
- ✅ Reactive queries con Flow

#### **🎨 UI/UX Design**
- ✅ Responsive layouts
- ✅ Accessibility principles
- ✅ Color theory y visual hierarchy
- ✅ User feedback y loading states
- ✅ Form validation y error handling

### 🎯 **Nivel de Complejidad**

| Concepto | Nivel | Descripción |
|----------|-------|-------------|
| **Kotlin Basics** | 🟢 Principiante | Variables, funciones, clases |
| **Compose UI** | 🟡 Intermedio | Composables, states, modifiers |
| **Room Database** | 🟡 Intermedio | Entities, DAOs, queries |
| **MVVM Architecture** | 🔴 Avanzado | ViewModels, LiveData, separation |
| **Reactive Programming** | 🔴 Avanzado | Flow, coroutines, reactive streams |

---

## 🚀 Próximas Funcionalidades

### 🔮 **Roadmap de Desarrollo**

#### **📊 v2.0 - Analytics Avanzados**
- 📈 Gráficos de ganancias por semana/mes
- 📊 Reportes de clientes más frecuentes
- 💹 Tendencias de ingresos
- 📉 Análisis de performance por tipo de mascota

#### **🔍 v2.1 - Búsqueda y Filtros**
- 🔍 Búsqueda por cliente o mascota
- 📅 Filtros por rango de fechas
- 💰 Filtros por monto
- 🐕 Filtros por tipo de mascota

#### **📱 v2.2 - Experiencia de Usuario**
- 🌙 Modo oscuro/claro
- 🔔 Notificaciones de recordatorio
- 📸 Fotos de mascotas
- 🗺️ Integración con mapas para rutas

#### **☁️ v3.0 - Sincronización**
- ☁️ Backup en la nube con Firebase
- 👥 Múltiples usuarios/dispositivos
- 📄 Exportación a PDF/Excel
- 🔐 Autenticación de usuarios

#### **🏢 v3.1 - Funciones Empresariales**
- 📊 Dashboard web para análisis
- 🤖 API REST para integraciones
- 📈 Business Intelligence
- 💳 Integración con pasarelas de pago

---

## 🤝 Contribución

### 👥 **Cómo Contribuir**

1. **Fork el proyecto**
2. **Crear rama para feature** (`git checkout -b feature/nueva-funcionalidad`)
3. **Commit cambios** (`git commit -m 'Add nueva funcionalidad'`)
4. **Push a la rama** (`git push origin feature/nueva-funcionalidad`)
5. **Abrir Pull Request**

### 📋 **Guidelines de Contribución**

- ✅ Seguir las convenciones de código Kotlin
- ✅ Agregar tests para nuevas funcionalidades
- ✅ Documentar cambios en el README
- ✅ Mantener compatibilidad con API 24+
- ✅ Usar commits descriptivos en español

### 🐛 **Reportar Bugs**

Para reportar bugs, incluye:
- 📱 Versión de Android
- 📋 Pasos para reproducir
- 📸 Screenshots si es posible
- 📝 Logs relevantes

---

## 📄 Licencia

```
MIT License

Copyright (c) 2025 Control de Paseos de Mascotas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 📞 Contacto

### 👨‍💻 **Desarrolladores**

- **GitHub**: [@sakhura](https://github.com/sakhura)
- **Email**: sabina.clases25@gmail.com

### 🌟 **Agradecimientos**

- 🎯 **Proyecto educativo** desarrollado para enseñar conceptos de Android moderno
- 🏗️ **Arquitectura inspirada** en las mejores prácticas de Google
- 🎨 **Diseño basado** en Material Design Guidelines
- 📚 **Documentación completa** para facilitar el aprendizaje

---

<div align="center">

### ⭐ **¡Si te gustó este proyecto, dale una estrella!** ⭐

**Hecho con ❤️ para la comunidad Android en Chile 🇨🇱**

</div>
