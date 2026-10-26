package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duocuc.flujoclientesrinconsur.data.local.entity.RegistroObservacionEntity
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository
import cl.duocuc.flujoclientesrinconsur.model.RegistroObservacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroObservacionViewModel(
    private val registroRepository: RegistroObservacionRepository,
    private val sesionRepository: SesionObservacionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        RegistroObservacionUiState()
    )

    val uiState: StateFlow<RegistroObservacionUiState> = _uiState

    fun sumarTransito() {

        if (_uiState.value.guardando || _uiState.value.finalizado) {
            return
        }

        _uiState.update {
            it.copy(
                transito = it.transito + 1,
                mensajeExito = null,
                mensajeError = null
            )
        }
    }

    fun sumarVisita() {

        if (_uiState.value.guardando || _uiState.value.finalizado) {
            return
        }

        _uiState.update {
            it.copy(
                visita = it.visita + 1,
                mensajeExito = null,
                mensajeError = null
            )
        }
    }

    fun sumarNoDeterminado() {

        if (_uiState.value.guardando || _uiState.value.finalizado) {
            return
        }

        _uiState.update {
            it.copy(
                noDeterminado = it.noDeterminado + 1,
                mensajeExito = null,
                mensajeError = null
            )
        }
    }

    fun guardarRegistro(
        sesionId: Int
    ) {

        val estado = _uiState.value

        if (estado.guardando || estado.finalizado) {
            return
        }

        val totalPersonas =
            estado.transito +
                    estado.visita +
                    estado.noDeterminado

        if (totalPersonas == 0) {

            _uiState.update {
                it.copy(
                    mensajeError = "Debes registrar al menos una persona"
                )
            }

            return
        }

        val registro = RegistroObservacionEntity(
            sesionId = sesionId,
            transito = estado.transito,
            visita = estado.visita,
            noDeterminado = estado.noDeterminado
        )

        _uiState.update {
            it.copy(
                guardando = true,
                mensajeExito = null,
                mensajeError = null
            )
        }

        viewModelScope.launch {

            try {

                val sesion = sesionRepository.obtenerSesionPorId(
                    sesionId
                )

                if (sesion == null) {

                    _uiState.update {
                        it.copy(
                            guardando = false,
                            mensajeError = "No se encontró la sesión"
                        )
                    }

                    return@launch
                }

                registroRepository.insertarRegistro(
                    registro
                )

                sesionRepository.actualizarSesion(
                    sesion.copy(
                        estado = "FINALIZADA"
                    )
                )

                _uiState.update {
                    it.copy(
                        guardando = false,
                        finalizado = true,
                        mensajeExito = "Registro guardado correctamente",
                        mensajeError = null
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        guardando = false,
                        mensajeError = "No se pudo guardar el registro"
                    )
                }
            }
        }
    }
}