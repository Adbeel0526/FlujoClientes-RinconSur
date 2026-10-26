package cl.duocuc.flujoclientesrinconsur.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import cl.duocuc.flujoclientesrinconsur.R

object NotificacionHelper {

    private const val CANAL_ID =
        "registro_observacion"

    private const val NOTIFICACION_ID =
        1

    fun mostrarNotificacion(
        context: Context
    ) {

        crearCanal(
            context
        )

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notificacion =
            NotificationCompat.Builder(
                context,
                CANAL_ID
            )
                .setSmallIcon(
                    R.drawable.ic_notification
                )
                .setContentTitle(
                    "Registro finalizado"
                )
                .setContentText(
                    "La observación fue guardada correctamente."
                )
                .setPriority(
                    NotificationCompat.PRIORITY_DEFAULT
                )
                .setAutoCancel(true)
                .build()

        NotificationManagerCompat
            .from(context)
            .notify(
                NOTIFICACION_ID,
                notificacion
            )
    }

    private fun crearCanal(
        context: Context
    ) {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val canal =
                NotificationChannel(
                    CANAL_ID,
                    "Registro de observación",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {

                    description =
                        "Notificaciones al finalizar una observación"
                }

            val manager =
                context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            manager.createNotificationChannel(
                canal
            )
        }
    }
}