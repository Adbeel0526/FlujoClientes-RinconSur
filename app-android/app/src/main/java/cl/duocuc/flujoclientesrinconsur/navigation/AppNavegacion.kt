package cl.duocuc.flujoclientesrinconsur.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duocuc.flujoclientesrinconsur.ui.screens.ObservadorScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SesionObservacionScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SupervisorScreen
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModel

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
                },
                onProgramarSesion = {
                    navController.navigate(Rutas.SESION_OBSERVACION)
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

        composable(Rutas.SESION_OBSERVACION) {

            val viewModel: SesionObservacionViewModel = viewModel()

            SesionObservacionScreen(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}