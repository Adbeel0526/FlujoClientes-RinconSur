package cl.duocuc.flujoclientesrinconsur.model

data class DashboardUiState(
    val sesionesTotales: Int = 0,
    val sesionesProgramadas: Int = 0,
    val sesionesFinalizadas: Int = 0,

    val registrosTotales: Int = 0,

    val totalTransito: Int = 0,
    val totalVisita: Int = 0,
    val totalNoDeterminado: Int = 0,
    val totalPersonas: Int = 0,

    val porcentajeTransito: Int = 0,
    val porcentajeVisita: Int = 0,
    val porcentajeNoDeterminado: Int = 0
)