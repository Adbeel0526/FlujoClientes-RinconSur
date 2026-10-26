package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import cl.duocuc.flujoclientesrinconsur.model.ObservadorUiState
import cl.duocuc.flujoclientesrinconsur.ui.theme.FondoApp
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoSecundario

@Composable
fun ObservadorScreen(
    estado: ObservadorUiState,
    onSeleccionarSesion: (Int) -> Unit,
    onIrSupervisor: () -> Unit
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
                text = "Panel Observador",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Text(
                text = "Selecciona una sesión para comenzar",
                fontSize = 14.sp,
                color = TextoSecundario
            )
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MoradoClaro
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = estado.sesiones.size.toString(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MoradoPrincipal
                    )

                    Text(
                        text = "Sesiones disponibles",
                        color = TextoSecundario
                    )
                }
            }
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

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "No hay sesiones disponibles",
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal
                        )

                        Text(
                            text = "El Supervisor debe programar una nueva sesión.",
                            color = TextoSecundario,
                            fontSize = 14.sp
                        )
                    }
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
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Sesión #${sesion.id}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoPrincipal
                        )

                        Text(
                            text = "PROGRAMADA",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoradoPrincipal
                        )
                    }

                    Text(
                        text = "Acceso ${sesion.acceso}",
                        fontWeight = FontWeight.SemiBold,
                        color = TextoPrincipal
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

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Button(
                        onClick = {
                            onSeleccionarSesion(
                                sesion.id
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MoradoPrincipal
                        )
                    ) {

                        Text(
                            text = "Iniciar observación",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        item {

            Button(
                onClick = onIrSupervisor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoradoClaro,
                    contentColor = MoradoPrincipal
                )
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