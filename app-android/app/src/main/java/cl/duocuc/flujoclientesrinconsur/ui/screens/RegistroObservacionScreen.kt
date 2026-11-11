package cl.duocuc.flujoclientesrinconsur.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import cl.duocuc.flujoclientesrinconsur.ui.theme.FondoApp
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.MoradoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.RojoError
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoPrincipal
import cl.duocuc.flujoclientesrinconsur.ui.theme.TextoSecundario
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeClaro
import cl.duocuc.flujoclientesrinconsur.ui.theme.VerdeExito
import cl.duocuc.flujoclientesrinconsur.util.NotificacionHelper
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

    val permisoNotificacion =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { permisoConcedido ->

            if (permisoConcedido) {

                NotificacionHelper.mostrarNotificacion(
                    context
                )
            }
        }

    LaunchedEffect(
        mensajeExito
    ) {

        if (
            mensajeExito ==
            "Registro guardado correctamente"
        ) {

            if (
                Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.TIRAMISU
            ) {

                val permisoConcedido =
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) ==
                            PackageManager.PERMISSION_GRANTED

                if (permisoConcedido) {

                    NotificacionHelper.mostrarNotificacion(
                        context
                    )

                } else {

                    permisoNotificacion.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                }

            } else {

                NotificacionHelper.mostrarNotificacion(
                    context
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoApp)
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Registro de observación",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )

        Text(
            text = "Registra cada persona según su clasificación.",
            fontSize = 14.sp,
            color = TextoSecundario
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            ContadorCard(
                titulo = "Tránsito",
                cantidad = transito,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            ContadorCard(
                titulo = "Visita",
                cantidad = visita,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            ContadorCard(
                titulo = "Otro",
                cantidad = noDeterminado,
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = {

                VibracionHelper.vibrar(
                    context
                )

                onSumarTransito()
            },
            enabled = !guardando && !finalizado,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "+ Registrar tránsito",
                fontWeight = FontWeight.SemiBold
            )
        }

        Button(
            onClick = {

                VibracionHelper.vibrar(
                    context
                )

                onSumarVisita()
            },
            enabled = !guardando && !finalizado,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "+ Registrar visita",
                fontWeight = FontWeight.SemiBold
            )
        }

        Button(
            onClick = {

                VibracionHelper.vibrar(
                    context
                )

                onSumarNoDeterminado()
            },
            enabled = !guardando && !finalizado,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "+ Registrar no determinado",
                fontWeight = FontWeight.SemiBold
            )
        }

        AnimatedVisibility(
            visible = mensajeError != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor =
                        Color(0xFFFFEBEE)
                )
            ) {

                Text(
                    text = mensajeError ?: "",
                    modifier = Modifier.padding(14.dp),
                    color = RojoError,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        AnimatedVisibility(
            visible = mensajeExito != null,
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
                    text = mensajeExito ?: "",
                    modifier = Modifier.padding(14.dp),
                    color = VerdeExito,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = onGuardarRegistro,
            enabled = !guardando && !finalizado,
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
                    when {

                        finalizado ->
                            "Registro finalizado"

                        guardando ->
                            "Guardando..."

                        else ->
                            "Finalizar y guardar registro"
                    },
                fontWeight = FontWeight.SemiBold
            )
        }

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
            modifier = Modifier.height(12.dp)
        )
    }
}

@Composable
private fun ContadorCard(
    titulo: String,
    cantidad: Int,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 16.dp
            )
        ) {

            Text(
                text = cantidad.toString(),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoPrincipal
            )

            Text(
                text = titulo,
                fontSize = 12.sp,
                color = TextoSecundario
            )
        }
    }
}