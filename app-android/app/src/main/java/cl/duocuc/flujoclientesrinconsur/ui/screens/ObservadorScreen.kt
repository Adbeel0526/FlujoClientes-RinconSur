package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ObservadorScreen(
    onIrSupervisor: () -> Unit,
    onIniciarRegistro: () -> Unit
) {

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Pantalla Observador"
        )

        Button(
            onClick = onIniciarRegistro,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar registro de observación")
        }

        Button(
            onClick = onIrSupervisor,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ir a Supervisor")
        }
    }
}