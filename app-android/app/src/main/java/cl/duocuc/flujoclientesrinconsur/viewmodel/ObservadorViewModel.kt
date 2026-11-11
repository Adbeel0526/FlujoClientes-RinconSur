package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository
import cl.duocuc.flujoclientesrinconsur.model.ObservadorUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ObservadorViewModel(
    sesionRepository: SesionObservacionRepository
) : ViewModel() {

    val uiState: StateFlow<ObservadorUiState> =
        sesionRepository.obtenerSesiones()
            .map { sesiones ->

                ObservadorUiState(
                    sesiones = sesiones.filter {
                        it.estado == "PROGRAMADA"
                    }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ObservadorUiState()
            )
}