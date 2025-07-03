package com.example.examenapp.utils

// Función que calcula la edad en años a partir de una fecha de nacimiento (día, mes, año)
fun calcularEdad(dia: Int, mes: Int, anio: Int): Int {
    val today = java.util.Calendar.getInstance()
    var edad = today.get(java.util.Calendar.YEAR) - anio
    // Si el cumpleaños aún no ha llegado este año, se resta 1 a la edad
    if (mes > today.get(java.util.Calendar.MONTH) + 1 ||
        (mes == today.get(java.util.Calendar.MONTH) + 1 && dia > today.get(java.util.Calendar.DAY_OF_MONTH))) {
        edad--
    }
    return edad
}

// Función que retorna el signo del zodiaco chino según el año de nacimiento
fun signoZodiacoChino(anio: Int): String {
    // Lista de los 12 signos del zodiaco chino en orden
    val signos = listOf(
        "rata", "buey", "tigre", "conejo", "dragon", "serpiente",
        "caballo", "cabra", "mono", "gallo", "perro", "cerdo"
    )
    // Retorna el signo correspondiente usando el módulo del año
    return signos[anio % 12]
}
