package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duocuc.flujoclientesrinconsur.viewmodel.SesionObservacionViewModel

@Composable
fun SesionObservacionScreen(
    viewModel: SesionObservacionViewModel,
    onVolver: () -> Unit
) {

    val estado = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Programar sesión de observación"
        )

        OutlinedTextField(
            value = estado.acceso,
            onValueChange = {
                viewModel.onAccesoCambia(it)
            },
            label = {
                Text(text = "Acceso")
            },
            isError = estado.errorAcceso != null,
            supportingText = {
                if (estado.errorAcceso != null) {
                    Text(text = estado.errorAcceso)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.fecha,
            onValueChange = {
                viewModel.onFechaCambia(it)
            },
            label = {
                Text(text = "Fecha")
            },
            isError = estado.errorFecha != null,
            supportingText = {
                if (estado.errorFecha != null) {
                    Text(text = estado.errorFecha)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.horaInicio,
            onValueChange = {
                viewModel.onHoraInicioCambia(it)
            },
            label = {
                Text(text = "Hora de inicio")
            },
            isError = estado.errorHoraInicio != null,
            supportingText = {
                if (estado.errorHoraInicio != null) {
                    Text(text = estado.errorHoraInicio)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.horaFin,
            onValueChange = {
                viewModel.onHoraFinCambia(it)
            },
            label = {
                Text(text = "Hora de término")
            },
            isError = estado.errorHoraFin != null,
            supportingText = {
                if (estado.errorHoraFin != null) {
                    Text(text = estado.errorHoraFin)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.observador,
            onValueChange = {
                viewModel.onObservadorCambia(it)
            },
            label = {
                Text(text = "Observador")
            },
            isError = estado.errorObservador != null,
            supportingText = {
                if (estado.errorObservador != null) {
                    Text(text = estado.errorObservador)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (estado.mensajeExito != null) {
            Text(
                text = estado.mensajeExito
            )
        }

        Button(
            onClick = {
                viewModel.programarSesion()
            },
            enabled = !estado.guardando,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (estado.guardando) {
                    "Guardando..."
                } else {
                    "Programar sesión"
                }
            )
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver")
        }
    }
}