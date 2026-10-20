package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duocuc.flujoclientesrinconsur.data.local.entity.RegistroObservacionEntity
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.model.RegistroObservacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroObservacionViewModel(
    private val repository: RegistroObservacionRepository
) : ViewModel() {

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

    fun guardarRegistro() {

        val estado = _uiState.value

        val registro = RegistroObservacionEntity(
            transito = estado.transito,
            visita = estado.visita,
            noDeterminado = estado.noDeterminado
        )

        viewModelScope.launch {
            repository.insertarRegistro(registro)
        }
    }
}