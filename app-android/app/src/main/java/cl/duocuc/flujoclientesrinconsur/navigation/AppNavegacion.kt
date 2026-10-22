package cl.duocuc.flujoclientesrinconsur.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duocuc.flujoclientesrinconsur.data.local.database.AppDatabase
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository
import cl.duocuc.flujoclientesrinconsur.ui.screens.ObservadorScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.RegistroObservacionScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SesionObservacionScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SupervisorScreen
import cl.duocuc.flujoclientesrinconsur.viewmodel.RegistroObservacionViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.RegistroObservacionViewModelFactory
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModelFactory

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
                },
                onIniciarRegistro = {
                    navController.navigate(Rutas.REGISTRO_OBSERVACION)
                }
            )
        }

        composable(Rutas.SESION_OBSERVACION) {

            val context = LocalContext.current

            val database = AppDatabase.obtenerBaseDatos(context)

            val repository = SesionObservacionRepository(
                database.sesionObservacionDao()
            )

            val factory = SesionObservacionViewModelFactory(repository)

            val viewModel: SesionObservacionViewModel = viewModel(
                factory = factory
            )

            SesionObservacionScreen(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(Rutas.REGISTRO_OBSERVACION) {

            val context = LocalContext.current

            val database = AppDatabase.obtenerBaseDatos(context)

            val repository = RegistroObservacionRepository(
                database.registroObservacionDao()
            )

            val factory = RegistroObservacionViewModelFactory(repository)

            val registroViewModel: RegistroObservacionViewModel = viewModel(
                factory = factory
            )

            val estado = registroViewModel.uiState.collectAsState().value

            RegistroObservacionScreen(
                transito = estado.transito,
                visita = estado.visita,
                noDeterminado = estado.noDeterminado,
                guardando = estado.guardando,
                mensajeExito = estado.mensajeExito,

                onSumarTransito = {
                    registroViewModel.sumarTransito()
                },

                onSumarVisita = {
                    registroViewModel.sumarVisita()
                },

                onSumarNoDeterminado = {
                    registroViewModel.sumarNoDeterminado()
                },

                onGuardarRegistro = {
                    registroViewModel.guardarRegistro()
                },

                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}