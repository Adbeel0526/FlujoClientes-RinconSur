package cl.duocuc.flujoclientesrinconsur.navigation

object Rutas {

    const val SUPERVISOR = "supervisor"

    const val OBSERVADOR = "observador"

    const val SESION_OBSERVACION = "sesion_observacion"

    const val REGISTRO_OBSERVACION = "registro_observacion"

    const val DASHBOARD = "dashboard"

    fun registroObservacion(
        sesionId: Int
    ): String {

        return "$REGISTRO_OBSERVACION/$sesionId"
    }
}