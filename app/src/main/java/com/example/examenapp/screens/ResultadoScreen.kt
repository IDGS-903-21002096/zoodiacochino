package com.example.examenapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examenapp.utils.readFromFile
import com.example.examenapp.R
import com.example.examenapp.model.Usuario
import com.example.examenapp.utils.calcularEdad
import com.example.examenapp.utils.signoZodiacoChino

@Composable
fun ResultadoScreen(navController: NavController, calificacion: Int) {
    val context = LocalContext.current
    // Variable para almacenar los datos del usuario leídos desde el archivo
    var datos by remember { mutableStateOf<String?>(null) }

    // Efecto que se lanza una sola vez para leer el archivo "usuario.txt"
    LaunchedEffect(Unit) {
        datos = readFromFile(context, "usuario.txt")
    }

    // Si aún no se han cargado los datos, mostrar mensaje de carga
    if (datos == null) {
        Text("Cargando datos...")
        return
    }

    // Separa los datos leídos por comas
    val partes = datos!!.split(",")

    // Verifica que haya suficientes datos
    if (partes.size < 7) {
        Text("Datos corruptos")
        return
    }

    // Construye el objeto Usuario con los datos recuperados
    val usuario = Usuario(
        nombre = partes[0],
        apaterno = partes[1],
        amaterno = partes[2],
        dia = partes[3].toIntOrNull() ?: 1,
        mes = partes[4].toIntOrNull() ?: 1,
        anio = partes[5].toIntOrNull() ?: 2000,
        sexo = partes[6]
    )

    // Calcula la edad y el signo zodiacal chino
    val edad = calcularEdad(usuario.dia, usuario.mes, usuario.anio)
    val signo = signoZodiacoChino(usuario.anio)

    // Diseño de la pantalla: columna centrada con espaciado
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            // Muestra los datos del usuario
            "Hola ${usuario.nombre} ${usuario.apaterno} ${usuario.amaterno}",
            style = MaterialTheme.typography.titleLarge
        )
        Text("Tienes $edad años")
        Text("Tu signo zodiacal chino es: ${signo.replaceFirstChar { it.uppercase() }}")
        Text("Calificación: $calificacion / 6")

        // Selecciona la imagen correspondiente al signo zodiacal
        val recursoImagen = when (signo) {
            "rata" -> R.drawable.rata
            "buey" -> R.drawable.buey
            "tigre" -> R.drawable.tigre
            "conejo" -> R.drawable.conejo
            "dragon" -> R.drawable.dragon
            "serpiente" -> R.drawable.serpiente
            "caballo" -> R.drawable.caballo
            "cabra" -> R.drawable.cabra
            "mono" -> R.drawable.mono
            "gallo" -> R.drawable.gallo
            "perro" -> R.drawable.perro
            "cerdo" -> R.drawable.cerdo
            else -> R.drawable.rata
        }

        // Muestra la imagen del signo zodiacal
        Image(
            painter = painterResource(id = recursoImagen),
            contentDescription = "Signo Zodiacal",
            modifier = Modifier.size(150.dp)
        )
    }
}