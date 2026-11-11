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
import cl.duocuc.flujoclientesrinconsur.ui.screens.DashboardScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.ObservadorScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.RegistroObservacionScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SesionObservacionScreen
import cl.duocuc.flujoclientesrinconsur.ui.screens.SupervisorScreen
import cl.duocuc.flujoclientesrinconsur.viewmodel.DashboardViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.DashboardViewModelFactory
import cl.duocuc.flujoclientesrinconsur.viewmodel.ObservadorViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.ObservadorViewModelFactory
import cl.duocuc.flujoclientesrinconsur.viewmodel.RegistroObservacionViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.RegistroObservacionViewModelFactory
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModelFactory
import cl.duocuc.flujoclientesrinconsur.viewmodel.SupervisorViewModel
import cl.duocuc.flujoclientesrinconsur.viewmodel.SupervisorViewModelFactory

@Composable
fun AppNavegacion() {

    val navController =
        rememberNavController()

    val context =
        LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Rutas.SUPERVISOR
    ) {

        composable(
            route = Rutas.SUPERVISOR
        ) {

            val database =
                AppDatabase.obtenerBaseDatos(
                    context
                )

            val sesionRepository =
                SesionObservacionRepository(
                    database.sesionObservacionDao()
                )

            val registroRepository =
                RegistroObservacionRepository(
                    database.registroObservacionDao()
                )

            val factory =
                SupervisorViewModelFactory(
                    sesionRepository,
                    registroRepository
                )

            val supervisorViewModel:
                    SupervisorViewModel =
                viewModel(
                    factory = factory
                )

            val estado =
                supervisorViewModel.uiState
                    .collectAsState()
                    .value

            SupervisorScreen(
                estado = estado,

                onIrObservador = {

                    navController.navigate(
                        Rutas.OBSERVADOR
                    )
                },

                onProgramarSesion = {

                    navController.navigate(
                        Rutas.SESION_OBSERVACION
                    )
                },

                onIrDashboard = {

                    navController.navigate(
                        Rutas.DASHBOARD
                    )
                }
            )
        }

        composable(
            route = Rutas.DASHBOARD
        ) {

            val database =
                AppDatabase.obtenerBaseDatos(
                    context
                )

            val sesionRepository =
                SesionObservacionRepository(
                    database.sesionObservacionDao()
                )

            val registroRepository =
                RegistroObservacionRepository(
                    database.registroObservacionDao()
                )

            val factory =
                DashboardViewModelFactory(
                    sesionRepository,
                    registroRepository
                )

            val dashboardViewModel:
                    DashboardViewModel =
                viewModel(
                    factory = factory
                )

            val estado =
                dashboardViewModel.uiState
                    .collectAsState()
                    .value

            DashboardScreen(
                estado = estado,

                onVolver = {

                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Rutas.OBSERVADOR
        ) {

            val database =
                AppDatabase.obtenerBaseDatos(
                    context
                )

            val sesionRepository =
                SesionObservacionRepository(
                    database.sesionObservacionDao()
                )

            val factory =
                ObservadorViewModelFactory(
                    sesionRepository
                )

            val observadorViewModel:
                    ObservadorViewModel =
                viewModel(
                    factory = factory
                )

            val estado =
                observadorViewModel.uiState
                    .collectAsState()
                    .value

            ObservadorScreen(
                estado = estado,

                onSeleccionarSesion = { sesionId ->

                    navController.navigate(
                        Rutas.registroObservacion(
                            sesionId
                        )
                    )
                },

                onIrSupervisor = {

                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Rutas.SESION_OBSERVACION
        ) {

            val database =
                AppDatabase.obtenerBaseDatos(
                    context
                )

            val repository =
                SesionObservacionRepository(
                    database.sesionObservacionDao()
                )

            val factory =
                SesionObservacionViewModelFactory(
                    repository
                )

            val sesionViewModel:
                    SesionObservacionViewModel =
                viewModel(
                    factory = factory
                )

            SesionObservacionScreen(
                viewModel = sesionViewModel,

                onVolver = {

                    navController.popBackStack()
                }
            )
        }

        composable(
            route =
                "${Rutas.REGISTRO_OBSERVACION}/{sesionId}"
        ) { backStackEntry ->

            val sesionId =
                backStackEntry.arguments
                    ?.getString(
                        "sesionId"
                    )
                    ?.toIntOrNull()
                    ?: 0

            val database =
                AppDatabase.obtenerBaseDatos(
                    context
                )

            val registroRepository =
                RegistroObservacionRepository(
                    database.registroObservacionDao()
                )

            val sesionRepository =
                SesionObservacionRepository(
                    database.sesionObservacionDao()
                )

            val factory =
                RegistroObservacionViewModelFactory(
                    registroRepository,
                    sesionRepository
                )

            val registroViewModel:
                    RegistroObservacionViewModel =
                viewModel(
                    factory = factory
                )

            val estado =
                registroViewModel.uiState
                    .collectAsState()
                    .value

            RegistroObservacionScreen(
                transito =
                    estado.transito,

                visita =
                    estado.visita,

                noDeterminado =
                    estado.noDeterminado,

                guardando =
                    estado.guardando,

                finalizado =
                    estado.finalizado,

                mensajeExito =
                    estado.mensajeExito,

                mensajeError =
                    estado.mensajeError,

                onSumarTransito = {

                    registroViewModel
                        .sumarTransito()
                },

                onSumarVisita = {

                    registroViewModel
                        .sumarVisita()
                },

                onSumarNoDeterminado = {

                    registroViewModel
                        .sumarNoDeterminado()
                },

                onGuardarRegistro = {

                    registroViewModel
                        .guardarRegistro(
                            sesionId
                        )
                },

                onVolver = {

                    navController.popBackStack()
                }
            )
        }
    }
}