package cl.duocuc.flujoclientesrinconsur.model

data class SesionObservacionUiState(

    val acceso: String = "",
    val fecha: String = "",
    val horaInicio: String = "",
    val horaFin: String = "",
    val observador: String = "",

    val errorAcceso: String? = null,
    val errorFecha: String? = null,
    val errorHoraInicio: String? = null,
    val errorHoraFin: String? = null,
    val errorObservador: String? = null,

    val guardando: Boolean = false,
    val mensajeExito: String? = null
)