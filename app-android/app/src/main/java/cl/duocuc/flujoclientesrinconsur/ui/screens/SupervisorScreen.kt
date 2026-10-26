package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duocuc.flujoclientesrinconsur.model.SupervisorUiState

@Composable
fun SupervisorScreen(
    estado: SupervisorUiState,
    onIrObservador: () -> Unit,
    onProgramarSesion: () -> Unit,
    onIrDashboard: () -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "Panel Supervisor"
            )
        }

        item {

            Button(
                onClick = onProgramarSesion,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Programar sesión"
                )
            }
        }

        item {

            Button(
                onClick = onIrObservador,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Ir a Observador"
                )
            }
        }

        item {

            Button(
                onClick = onIrDashboard,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Ver dashboard"
                )
            }
        }

        item {

            Text(
                text = "Sesiones: ${estado.sesiones.size}"
            )
        }

        items(
            estado.sesiones
        ) { sesion ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        text = "Sesión #${sesion.id}"
                    )

                    Text(
                        text = "Acceso: ${sesion.acceso}"
                    )

                    Text(
                        text = "Fecha: ${sesion.fecha}"
                    )

                    Text(
                        text = "Horario: ${sesion.horaInicio} - ${sesion.horaFin}"
                    )

                    Text(
                        text = "Observador: ${sesion.observador}"
                    )

                    Text(
                        text = "Estado: ${sesion.estado}"
                    )
                }
            }
        }

        item {

            Text(
                text = "Registros de observación: ${estado.registros.size}"
            )
        }

        items(
            estado.registros
        ) { registro ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        text = "Registro #${registro.id}"
                    )

                    Text(
                        text = "Sesión #${registro.sesionId}"
                    )

                    Text(
                        text = "Tránsito: ${registro.transito}"
                    )

                    Text(
                        text = "Visita: ${registro.visita}"
                    )

                    Text(
                        text = "No determinado: ${registro.noDeterminado}"
                    )
                }
            }
        }
    }
}