package cl.duocuc.flujoclientesrinconsur.model

import cl.duocuc.flujoclientesrinconsur.data.local.entity.SesionObservacionEntity

data class ObservadorUiState(
    val sesiones: List<SesionObservacionEntity> = emptyList()
)