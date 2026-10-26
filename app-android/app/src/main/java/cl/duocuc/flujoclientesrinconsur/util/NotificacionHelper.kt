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

object NotificacionHelper {

    private const val CANAL_ID = "registro_observacion"
    private const val NOTIFICACION_ID = 1

    fun crearCanal(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val nombre = "Registros de observación"

            val descripcion =
                "Notificaciones al finalizar registros de observación"

            val importancia =
                NotificationManager.IMPORTANCE_DEFAULT

            val canal = NotificationChannel(
                CANAL_ID,
                nombre,
                importancia
            ).apply {
                description = descripcion
            }

            val notificationManager =
                context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            notificationManager.createNotificationChannel(
                canal
            )
        }
    }

    fun mostrarNotificacion(
        context: Context
    ) {

        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        crearCanal(context)

        val notificacion =
            NotificationCompat.Builder(
                context,
                CANAL_ID
            )
                .setSmallIcon(
                    android.R.drawable.ic_dialog_info
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
}