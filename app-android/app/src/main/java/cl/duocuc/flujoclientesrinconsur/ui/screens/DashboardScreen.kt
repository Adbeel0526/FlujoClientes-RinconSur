package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duocuc.flujoclientesrinconsur.model.DashboardUiState
import cl.duocuc.flujoclientesrinconsur.ui.theme.FondoApp
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoSecundario
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeExito

@Composable
fun DashboardScreen(
    estado: DashboardUiState,
    onVolver: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoApp)
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Dashboard",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Text(
                text = "Resumen del flujo de clientes",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                TarjetaResumenDashboard(
                    titulo = "Sesiones",
                    valor = estado.sesionesTotales.toString(),
                    modifier = Modifier.weight(1f)
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                TarjetaResumenDashboard(
                    titulo = "Personas",
                    valor = estado.totalPersonas.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = "Estado de sesiones",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoPrincipal
                    )

                    Text(
                        text = "Programadas: ${estado.sesionesProgramadas}",
                        color = TextoSecundario
                    )

                    Text(
                        text = "Finalizadas: ${estado.sesionesFinalizadas}",
                        color = VerdeExito,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Registros realizados: ${estado.registrosTotales}",
                        color = TextoSecundario
                    )
                }
            }
        }

        item {

            IndicadorFlujo(
                titulo = "Tránsito",
                cantidad = estado.totalTransito,
                porcentaje = estado.porcentajeTransito
            )
        }

        item {

            IndicadorFlujo(
                titulo = "Visitas",
                cantidad = estado.totalVisita,
                porcentaje = estado.porcentajeVisita
            )
        }

        item {

            IndicadorFlujo(
                titulo = "No determinado",
                cantidad = estado.totalNoDeterminado,
                porcentaje = estado.porcentajeNoDeterminado
            )
        }

        item {

            Button(
                onClick = onVolver,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "Volver al Supervisor",
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
private fun TarjetaResumenDashboard(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MoradoClaro
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = valor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoPrincipal
            )

            Text(
                text = titulo,
                fontSize = 14.sp,
                color = TextoSecundario
            )
        }
    }
}

@Composable
private fun IndicadorFlujo(
    titulo: String,
    cantidad: Int,
    porcentaje: Int
) {

    val progresoObjetivo =
        porcentaje.coerceIn(
            0,
            100
        ) / 100f

    val progresoAnimado by animateFloatAsState(
        targetValue = progresoObjetivo,
        animationSpec = tween(
            durationMillis = 800
        ),
        label = "animacionIndicador"
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = titulo,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )

                Text(
                    text = "$porcentaje%",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoPrincipal
                )
            }

            Text(
                text = "$cantidad personas",
                color = TextoSecundario,
                fontSize = 14.sp
            )

            LinearProgressIndicator(
                progress = {
                    progresoAnimado
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = MoradoPrincipal,
                trackColor = MoradoClaro
            )
        }
    }
}