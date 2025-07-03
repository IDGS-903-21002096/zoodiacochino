package com.example.examenapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examenapp.screens.ExamenScreen
import com.example.examenapp.screens.FormularioScreen
import com.example.examenapp.screens.ResultadoScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    // Define el contenedor de navegación y la pantalla de inicio ("formulario")
    NavHost(navController = navController, startDestination = "formulario") {
        // Pantalla del formulario, se muestra al iniciar la app
        composable("formulario") { FormularioScreen(navController) }
        composable("examen") { ExamenScreen(navController) }

        // Pantalla de resultados con un parámetro llamado "calificacion"
        composable(
            route = "resultado/{calificacion}",
            arguments = listOf(navArgument("calificacion") { type = NavType.IntType })
        ) { backStackEntry ->
            // Obtiene la calificación desde los argumentos de la navegación
            val calificacion = backStackEntry.arguments?.getInt("calificacion") ?: 0
            // Muestra la pantalla de resultados con la calificación obtenida
            ResultadoScreen(navController, calificacion)
        }
    }
}