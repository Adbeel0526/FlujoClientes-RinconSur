package cl.duocuc.flujoclientesrinconsur.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.duocuc.flujoclientesrinconsur.util.VibracionHelper

@Composable
fun RegistroObservacionScreen(
    transito: Int,
    visita: Int,
    noDeterminado: Int,
    guardando: Boolean,
    finalizado: Boolean,
    mensajeExito: String?,
    mensajeError: String?,
    onSumarTransito: () -> Unit,
    onSumarVisita: () -> Unit,
    onSumarNoDeterminado: () -> Unit,
    onGuardarRegistro: () -> Unit,
    onVolver: () -> Unit
) {

    val context = LocalContext.current

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
            onClick = {
                VibracionHelper.vibrar(context)
                onSumarTransito()
            },
            enabled = !guardando && !finalizado,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Registrar tránsito"
            )
        }

        Text(
            text = "Visita: $visita"
        )

        Button(
            onClick = {
                VibracionHelper.vibrar(context)
                onSumarVisita()
            },
            enabled = !guardando && !finalizado,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Registrar visita"
            )
        }

        Text(
            text = "No determinado: $noDeterminado"
        )

        Button(
            onClick = {
                VibracionHelper.vibrar(context)
                onSumarNoDeterminado()
            },
            enabled = !guardando && !finalizado,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Registrar no determinado"
            )
        }

        if (mensajeError != null) {
            Text(
                text = mensajeError
            )
        }

        if (mensajeExito != null) {
            Text(
                text = mensajeExito
            )
        }

        Button(
            onClick = onGuardarRegistro,
            enabled = !guardando && !finalizado,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    when {
                        finalizado -> "Registro finalizado"
                        guardando -> "Guardando..."
                        else -> "Finalizar y guardar registro"
                    }
            )
        }

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Volver"
            )
        }
    }
}