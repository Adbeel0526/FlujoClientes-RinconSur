package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duocuc.flujoclientesrinconsur.model.SupervisorUiState
import cl.duocuc.flujoclientesrinconsur.ui.theme.FondoApp
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.NaranjaClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.NaranjaPendiente
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoSecundario
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeExito

@Composable
fun SupervisorScreen(
    estado: SupervisorUiState,
    onIrObservador: () -> Unit,
    onProgramarSesion: () -> Unit,
    onIrDashboard: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoApp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Panel Supervisor",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Text(
                text = "Resumen general de las sesiones",
                fontSize = 14.sp,
                color = TextoSecundario
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                ResumenCard(
                    titulo = "Sesiones",
                    valor = estado.sesiones.size.toString(),
                    modifier = Modifier.weight(1f)
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                ResumenCard(
                    titulo = "Registros",
                    valor = estado.registros.size.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {

            Button(
                onClick = onProgramarSesion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoradoPrincipal
                )
            ) {

                Text(
                    text = "Programar nueva sesión",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = onIrObservador,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MoradoClaro,
                        contentColor = MoradoPrincipal
                    )
                ) {

                    Text(
                        text = "Observador",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Button(
                    onClick = onIrDashboard,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MoradoClaro,
                        contentColor = MoradoPrincipal
                    )
                ) {

                    Text(
                        text = "Dashboard",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        item {

            Text(
                text = "Sesiones",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
        }

        if (estado.sesiones.isEmpty()) {

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Text(
                        text = "No hay sesiones registradas",
                        modifier = Modifier.padding(18.dp),
                        color = TextoSecundario
                    )
                }
            }
        }

        items(
            estado.sesiones
        ) { sesion ->

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
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Sesión #${sesion.id}",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal
                        )

                        EstadoSesion(
                            estado = sesion.estado
                        )
                    }

                    Text(
                        text = "Acceso ${sesion.acceso}",
                        color = TextoPrincipal,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "${sesion.fecha} · ${sesion.horaInicio} - ${sesion.horaFin}",
                        color = TextoSecundario,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Observador: ${sesion.observador}",
                        color = TextoSecundario,
                        fontSize = 14.sp
                    )
                }
            }
        }

        item {

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}

@Composable
private fun ResumenCard(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
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
private fun EstadoSesion(
    estado: String
) {

    val colorFondo =
        if (estado == "FINALIZADA") {
            VerdeClaro
        } else {
            NaranjaClaro
        }

    val colorTexto =
        if (estado == "FINALIZADA") {
            VerdeExito
        } else {
            NaranjaPendiente
        }

    Box(
        modifier = Modifier
            .background(
                color = colorFondo,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
    ) {

        Text(
            text = estado,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = colorTexto
        )
    }
}