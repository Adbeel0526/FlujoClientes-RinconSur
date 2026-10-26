package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository
import cl.duocuc.flujoclientesrinconsur.model.SupervisorUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SupervisorViewModel(
    sesionRepository: SesionObservacionRepository,
    registroRepository: RegistroObservacionRepository
) : ViewModel() {

    val uiState: StateFlow<SupervisorUiState> = combine(
        sesionRepository.obtenerSesiones(),
        registroRepository.obtenerRegistros()
    ) { sesiones, registros ->

        SupervisorUiState(
            sesiones = sesiones,
            registros = registros
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SupervisorUiState()
    )
}