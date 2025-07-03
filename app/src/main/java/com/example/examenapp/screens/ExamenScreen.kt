package com.example.examenapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Data class que representa una pregunta del examen
data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int
)

@Composable
fun ExamenScreen(navController: NavController) {
    // Lista de preguntas que se mostrarán en el examen
    val preguntas = listOf(
        Pregunta("¿Cuál es el océano más grande del mundo?", listOf("Atlántico", "Índico", "Pacífico", "Ártico"), 2),
        Pregunta("¿Cuál es la capital de México?", listOf("Lima", "Madrid", "CDMX", "Bogotá"), 2),
        Pregunta("¿Cuál es el planeta más cercano al Sol?", listOf("Venus", "Tierra", "Mercurio", "Marte"), 2),
        Pregunta("¿Cuál es el símbolo químico del oro?", listOf("Au", "Ag", "Fe", "O"), 0),
        Pregunta("¿Qué animal es conocido como el rey de la selva?", listOf("Tigre", "Elefante", "León", "Jaguar"), 2),
        Pregunta("¿Qué instrumento tiene teclas, cuerdas y martillos?", listOf("Violín", "Guitarra", "Piano", "Arpa"), 2)
    )

    // Lista mutable que guarda las respuestas del usuario (inicialmente todas en -1)
    val respuestasUsuario = remember { mutableStateListOf(-1, -1, -1, -1, -1, -1) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Examen", style = MaterialTheme.typography.titleLarge)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(preguntas.size) { index ->
                val pregunta = preguntas[index]
                Column {
                    // Muestra el texto de la pregunta
                    Text("${index + 1}. ${pregunta.texto}")
                    // Muestra las opciones con botones de opción (radio buttons)
                    pregunta.opciones.forEachIndexed { i, opcion ->
                        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            RadioButton(
                                selected = respuestasUsuario[index] == i, // Selecciona si coincide con la respuesta elegida
                                onClick = { respuestasUsuario[index] = i } // Al hacer clic, guarda la respuesta del usuario
                            )
                            Text(opcion)
                        }
                    }
                }
            }
        }

        // Botón para terminar el examen
        Button(
            onClick = {
                // Calcula cuántas respuestas fueron correctas
                val correctas = preguntas.zip(respuestasUsuario).count {
                    it.second == it.first.respuestaCorrecta
                }
                // Navega a la pantalla de resultados pasando el número de aciertos como parámetro
                navController.navigate("resultado/$correctas")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Terminar")
        }
    }
}
