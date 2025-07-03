package com.example.examenapp.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.OutputStreamWriter

// Función auxiliar para guardar datos en un archivo local en el almacenamiento interno
fun saveToFile(context: Context, filename: String, data: String) {
    val outputStreamWriter = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
    outputStreamWriter.use {
        it.write(data)
    }
}

@Composable
fun FormularioScreen(navController: NavController) {
    // Contexto de la app para acceder al almacenamiento
    val context = LocalContext.current
    // Variables de estado para cada campo del formulario
    var nombre by remember { mutableStateOf("") }
    var apaterno by remember { mutableStateOf("") }
    var amaterno by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var mes by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("Hombre") }

    // Layout general en forma de columna con padding y separación entre elementos
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Título del formulario
        Text("Formulario", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)

        // Campos de texto para los nombres
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apaterno,
            onValueChange = { apaterno = it },
            label = { Text("Apellido Paterno") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = amaterno,
            onValueChange = { amaterno = it },
            label = { Text("Apellido Materno") },
            modifier = Modifier.fillMaxWidth()
        )

        // Fila para la fecha de nacimiento: Día, Mes y Año
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = dia,
                onValueChange = { dia = it },
                label = { Text("Día") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = mes,
                onValueChange = { mes = it },
                label = { Text("Mes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                label = { Text("Año") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }

        // Selección del sexo usando botones de opción (radio)
        Text("Sexo:")
        Row {
            RadioButton(
                selected = sexo == "Hombre",
                onClick = { sexo = "Hombre" }
            )
            Text("Hombre", modifier = Modifier.padding(end = 16.dp))
            RadioButton(
                selected = sexo == "Mujer",
                onClick = { sexo = "Mujer" }
            )
            Text("Mujer")
        }

        // Botones para limpiar el formulario o avanzar a la siguiente pantalla
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                nombre = ""
                apaterno = ""
                amaterno = ""
                dia = ""
                mes = ""
                anio = ""
                sexo = "Hombre"
            }) {
                Text("Limpiar")
            }

            // Botón que guarda los datos en un archivo y navega a la pantalla de examen
            Button(onClick = {
                val data = "$nombre,$apaterno,$amaterno,$dia,$mes,$anio,$sexo"
                // Guardar en archivo
                saveToFile(context, "usuario.txt", data)
                navController.navigate("examen")
            }) {
                Text("Siguiente")
            }
        }
    }
}