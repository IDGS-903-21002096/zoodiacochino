package com.example.examenapp.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

// Función que lee una línea de texto desde un archivo almacenado en el almacenamiento interno
fun readFromFile(context: Context, filename: String): String? {
    return try {
        // Abre el archivo para lectura desde el almacenamiento interno
        val inputStream = context.openFileInput(filename)
        // Crea un lector de texto a partir del InputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        // Lee una sola línea del archivo (ideal si el archivo tiene solo una línea con los datos)
        val content = reader.readLine()
        // Cierra el lector para liberar recursos
        reader.close()
        // Retorna el contenido leído
        content
    } catch (e: Exception) {
        // Si ocurre algún error (archivo no existe, error de lectura, etc.), retorna null
        null
    }
}