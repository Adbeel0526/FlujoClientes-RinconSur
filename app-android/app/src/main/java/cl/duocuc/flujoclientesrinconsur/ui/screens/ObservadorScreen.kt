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
import cl.duocuc.flujoclientesrinconsur.model.ObservadorUiState

@Composable
fun ObservadorScreen(
    estado: ObservadorUiState,
    onSeleccionarSesion: (Int) -> Unit,
    onIrSupervisor: () -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Panel Observador"
            )
        }

        item {
            Text(
                text = "Sesiones disponibles: ${estado.sesiones.size}"
            )
        }

        if (estado.sesiones.isEmpty()) {

            item {
                Text(
                    text = "No hay sesiones programadas disponibles"
                )
            }
        }

        items(estado.sesiones) { sesion ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
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

                    Button(
                        onClick = {
                            onSeleccionarSesion(sesion.id)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Iniciar observación"
                        )
                    }
                }
            }
        }

        item {

            Button(
                onClick = onIrSupervisor,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Volver al Supervisor"
                )
            }
        }
    }
}