package cl.duocuc.flujoclientesrinconsur.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duocuc.flujoclientesrinconsur.ui.screens.ObservadorScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SupervisorScreen

@Composable
fun AppNavegacion() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.SUPERVISOR
    ) {

        composable(Rutas.SUPERVISOR) {
            SupervisorScreen(
                onIrObservador = {
                    navController.navigate(Rutas.OBSERVADOR)
                }
            )
        }

        composable(Rutas.OBSERVADOR) {
            ObservadorScreen(
                onIrSupervisor = {
                    navController.navigate(Rutas.SUPERVISOR)
                }
            )
        }
    }
}