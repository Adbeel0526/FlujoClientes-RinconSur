package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository
import cl.duocuc.flujoclientesrinconsur.model.DashboardUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    sesionRepository: SesionObservacionRepository,
    registroRepository: RegistroObservacionRepository
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = combine(
        sesionRepository.obtenerSesiones(),
        registroRepository.obtenerRegistros()
    ) { sesiones, registros ->

        val sesionesProgramadas = sesiones.count {
            it.estado == "PROGRAMADA"
        }

        val sesionesFinalizadas = sesiones.count {
            it.estado == "FINALIZADA"
        }

        val totalTransito = registros.sumOf {
            it.transito
        }

        val totalVisita = registros.sumOf {
            it.visita
        }

        val totalNoDeterminado = registros.sumOf {
            it.noDeterminado
        }

        val totalPersonas =
            totalTransito +
                    totalVisita +
                    totalNoDeterminado

        val porcentajeTransito =
            if (totalPersonas > 0) {
                (totalTransito * 100) / totalPersonas
            } else {
                0
            }

        val porcentajeVisita =
            if (totalPersonas > 0) {
                (totalVisita * 100) / totalPersonas
            } else {
                0
            }

        val porcentajeNoDeterminado =
            if (totalPersonas > 0) {
                (totalNoDeterminado * 100) / totalPersonas
            } else {
                0
            }

        DashboardUiState(
            sesionesTotales = sesiones.size,
            sesionesProgramadas = sesionesProgramadas,
            sesionesFinalizadas = sesionesFinalizadas,

            registrosTotales = registros.size,

            totalTransito = totalTransito,
            totalVisita = totalVisita,
            totalNoDeterminado = totalNoDeterminado,
            totalPersonas = totalPersonas,

            porcentajeTransito = porcentajeTransito,
            porcentajeVisita = porcentajeVisita,
            porcentajeNoDeterminado = porcentajeNoDeterminado
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DashboardUiState()
    )
}