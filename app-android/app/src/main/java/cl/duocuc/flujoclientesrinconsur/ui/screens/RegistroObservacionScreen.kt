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
fun RegistroObservacionScreen(
    transito: Int,
    visita: Int,
    noDeterminado: Int,
    onSumarTransito: () -> Unit,
    onSumarVisita: () -> Unit,
    onSumarNoDeterminado: () -> Unit,
    onGuardarRegistro: () -> Unit,
    onVolver: () -> Unit
) {

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Registro de observación"
        )

        Text(
            text = "Tránsito: $transito"
        )

        Button(
            onClick = onSumarTransito,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar tránsito")
        }

        Text(
            text = "Visita: $visita"
        )

        Button(
            onClick = onSumarVisita,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar visita")
        }

        Text(
            text = "No determinado: $noDeterminado"
        )

        Button(
            onClick = onSumarNoDeterminado,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar no determinado")
        }

        Button(
            onClick = onGuardarRegistro,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Finalizar y guardar registro")
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver")
        }
    }
}