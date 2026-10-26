package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duocuc.flujoclientesrinconsur.model.DashboardUiState

@Composable
fun DashboardScreen(
    estado: DashboardUiState,
    onVolver: () -> Unit
) {

    LazyColumn(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Dashboard"
            )
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Sesiones"
                    )

                    Text(
                        text = "Total: ${estado.sesionesTotales}"
                    )

                    Text(
                        text = "Programadas: ${estado.sesionesProgramadas}"
                    )

                    Text(
                        text = "Finalizadas: ${estado.sesionesFinalizadas}"
                    )
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Resumen de observaciones"
                    )

                    Text(
                        text = "Registros realizados: ${estado.registrosTotales}"
                    )

                    Text(
                        text = "Personas observadas: ${estado.totalPersonas}"
                    )
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Tránsito"
                    )

                    Text(
                        text = "Total: ${estado.totalTransito}"
                    )

                    Text(
                        text = "${estado.porcentajeTransito}% del flujo observado"
                    )
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Visitas"
                    )

                    Text(
                        text = "Total: ${estado.totalVisita}"
                    )

                    Text(
                        text = "${estado.porcentajeVisita}% del flujo observado"
                    )
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "No determinado"
                    )

                    Text(
                        text = "Total: ${estado.totalNoDeterminado}"
                    )

                    Text(
                        text = "${estado.porcentajeNoDeterminado}% del flujo observado"
                    )
                }
            }
        }

        item {

            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Volver al Supervisor"
                )
            }
        }
    }
}