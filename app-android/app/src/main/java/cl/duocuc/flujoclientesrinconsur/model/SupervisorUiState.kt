package cl.duocuc.flujoclientesrinconsur.model

import cl.duocuc.flujoclientesrinconsur.data.local.entity.RegistroObservacionEntity
import cl.duocuc.flujoclientesrinconsur.data.local.entity.SesionObservacionEntity

data class SupervisorUiState(
    val sesiones: List<SesionObservacionEntity> = emptyList(),
    val registros: List<RegistroObservacionEntity> = emptyList()
)