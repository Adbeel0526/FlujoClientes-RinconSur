package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SupervisorScreen(
    onIrObservador: () -> Unit,
    onProgramarSesion: () -> Unit
) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Pantalla Supervisor"
        )

        Button(
            onClick = onIrObservador
        ) {
            Text(
                text = "Ir a Observador"
            )
        }

        Button(
            onClick = onProgramarSesion
        ) {
            Text(
                text = "Programar sesión"
            )
        }
    }
}