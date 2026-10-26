package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duocuc.flujoclientesrinconsur.ui.theme.FondoApp
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoSecundario
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeExito
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModel

@Composable
fun SesionObservacionScreen(
    viewModel: SesionObservacionViewModel,
    onVolver: () -> Unit
) {

    val estado =
        viewModel.uiState
            .collectAsState()
            .value

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
                text = "Programar sesión",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )

            Text(
                text = "Completa los datos para crear una nueva sesión de observación.",
                fontSize = 14.sp,
                color = TextoSecundario
            )
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
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    Text(
                        text = "Datos de la sesión",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoPrincipal
                    )

                    OutlinedTextField(
                        value = estado.acceso,
                        onValueChange = {
                            viewModel.onAccesoCambia(it)
                        },
                        label = {
                            Text("Acceso")
                        },
                        placeholder = {
                            Text("1, 2, 3 o 4")
                        },
                        isError = estado.errorAcceso != null,
                        supportingText = {

                            estado.errorAcceso?.let {
                                Text(it)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = estado.fecha,
                        onValueChange = {
                            viewModel.onFechaCambia(it)
                        },
                        label = {
                            Text("Fecha")
                        },
                        placeholder = {
                            Text("dd/MM/yyyy")
                        },
                        isError = estado.errorFecha != null,
                        supportingText = {

                            estado.errorFecha?.let {
                                Text(it)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = estado.horaInicio,
                        onValueChange = {
                            viewModel.onHoraInicioCambia(it)
                        },
                        label = {
                            Text("Hora de inicio")
                        },
                        placeholder = {
                            Text("10:00")
                        },
                        isError = estado.errorHoraInicio != null,
                        supportingText = {

                            estado.errorHoraInicio?.let {
                                Text(it)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = estado.horaFin,
                        onValueChange = {
                            viewModel.onHoraFinCambia(it)
                        },
                        label = {
                            Text("Hora de término")
                        },
                        placeholder = {
                            Text("10:45")
                        },
                        isError = estado.errorHoraFin != null,
                        supportingText = {

                            estado.errorHoraFin?.let {
                                Text(it)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = estado.observador,
                        onValueChange = {
                            viewModel.onObservadorCambia(it)
                        },
                        label = {
                            Text("Observador")
                        },
                        placeholder = {
                            Text("Nombre del observador")
                        },
                        isError = estado.errorObservador != null,
                        supportingText = {

                            estado.errorObservador?.let {
                                Text(it)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {

            AnimatedVisibility(
                visible = estado.mensajeExito != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = VerdeClaro
                    )
                ) {

                    Text(
                        text = estado.mensajeExito ?: "",
                        modifier = Modifier.padding(16.dp),
                        color = VerdeExito,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        item {

            Button(
                onClick = {
                    viewModel.programarSesion()
                },
                enabled = !estado.guardando,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoradoPrincipal
                )
            ) {

                Text(
                    text =
                        if (estado.guardando) {
                            "Guardando..."
                        } else {
                            "Programar sesión"
                        },
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        item {

            Button(
                onClick = onVolver,
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
                    text = "Volver"
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}