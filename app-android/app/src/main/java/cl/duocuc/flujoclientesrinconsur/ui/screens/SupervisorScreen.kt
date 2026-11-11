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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        item {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Panel Supervisor",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Text(
                text = "Resumen general de las sesiones",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                ResumenCard(
                    valor = estado.sesiones.size.toString(),
                    titulo = "Sesiones",
                    modifier = Modifier.weight(1f)
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                ResumenCard(
                    valor = estado.registros.size.toString(),
                    titulo = "Registros",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {

            Button(
                onClick = onProgramarSesion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
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
                    shape = RoundedCornerShape(18.dp),
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

        } else {

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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                            fontWeight = FontWeight.SemiBold,
                            color = TextoPrincipal
                        )

                        Text(
                            text = "${sesion.fecha} · ${sesion.horaInicio} - ${sesion.horaFin}",
                            fontSize = 14.sp,
                            color = TextoSecundario
                        )

                        Text(
                            text = "Observador: ${sesion.observador}",
                            fontSize = 14.sp,
                            color = TextoSecundario
                        )
                    }
                }
            }
        }

        if (estado.registros.isNotEmpty()) {

            item {

                Text(
                    text = "Registros",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
            }

            items(
                estado.registros
            ) { registro ->

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
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {

                        Text(
                            text = "Registro #${registro.id}",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal
                        )

                        Text(
                            text = "Sesión #${registro.sesionId}",
                            color = TextoSecundario
                        )

                        Text(
                            text = "Tránsito: ${registro.transito}",
                            color = TextoSecundario
                        )

                        Text(
                            text = "Visita: ${registro.visita}",
                            color = TextoSecundario
                        )

                        Text(
                            text = "No determinado: ${registro.noDeterminado}",
                            color = TextoSecundario
                        )
                    }
                }
            }
        }

        item {

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}

@Composable
private fun ResumenCard(
    valor: String,
    titulo: String,
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

    val esFinalizada =
        estado == "FINALIZADA"

    val fondo =
        if (esFinalizada) {
            VerdeClaro
        } else {
            NaranjaClaro
        }

    val texto =
        if (esFinalizada) {
            VerdeExito
        } else {
            NaranjaPendiente
        }

    Box(
        modifier = Modifier
            .background(
                color = fondo,
                shape = RoundedCornerShape(20.dp)
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
            color = texto
        )
    }
}