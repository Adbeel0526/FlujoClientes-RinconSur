package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import cl.duocuc.flujoclientesrinconsur.model.RegistroObservacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegistroObservacionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        RegistroObservacionUiState()
    )

    val uiState: StateFlow<RegistroObservacionUiState> = _uiState

    fun sumarTransito() {
        _uiState.update {
            it.copy(
                transito = it.transito + 1
            )
        }
    }

    fun sumarVisita() {
        _uiState.update {
            it.copy(
                visita = it.visita + 1
            )
        }
    }

    fun sumarNoDeterminado() {
        _uiState.update {
            it.copy(
                noDeterminado = it.noDeterminado + 1
            )
        }
    }
}