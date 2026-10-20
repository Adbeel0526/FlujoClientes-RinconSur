package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duocuc.flujoclientesrinconsur.data.local.entity.SesionObservacionEntity
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository
import cl.duocuc.flujoclientesrinconsur.model.SesionObservacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SesionObservacionViewModel(
    private val repository: SesionObservacionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SesionObservacionUiState())

    val uiState: StateFlow<SesionObservacionUiState> = _uiState

    fun onAccesoCambia(valor: String) {
        _uiState.update {
            it.copy(
                acceso = valor,
                errorAcceso = null,
                mensajeExito = null
            )
        }
    }

    fun onFechaCambia(valor: String) {
        _uiState.update {
            it.copy(
                fecha = valor,
                errorFecha = null,
                mensajeExito = null
            )
        }
    }

    fun onHoraInicioCambia(valor: String) {
        _uiState.update {
            it.copy(
                horaInicio = valor,
                errorHoraInicio = null,
                mensajeExito = null
            )
        }
    }

    fun onHoraFinCambia(valor: String) {
        _uiState.update {
            it.copy(
                horaFin = valor,
                errorHoraFin = null,
                mensajeExito = null
            )
        }
    }

    fun onObservadorCambia(valor: String) {
        _uiState.update {
            it.copy(
                observador = valor,
                errorObservador = null,
                mensajeExito = null
            )
        }
    }

    fun validarFormulario(): Boolean {

        val estado = _uiState.value

        val accesoValido = estado.acceso.isNotBlank()
        val fechaValida = estado.fecha.isNotBlank()
        val horaInicioValida = estado.horaInicio.isNotBlank()
        val horaFinValida = estado.horaFin.isNotBlank()
        val observadorValido = estado.observador.isNotBlank()

        val inicioMinutos = convertirHoraAMinutos(estado.horaInicio)
        val finMinutos = convertirHoraAMinutos(estado.horaFin)

        val duracionValida =
            inicioMinutos != null &&
                    finMinutos != null &&
                    (finMinutos - inicioMinutos) in 30..60

        _uiState.update {
            it.copy(
                errorAcceso =
                    if (accesoValido) {
                        null
                    } else {
                        "Debes seleccionar un acceso"
                    },

                errorFecha =
                    if (fechaValida) {
                        null
                    } else {
                        "Debes ingresar una fecha"
                    },

                errorHoraInicio =
                    if (horaInicioValida) {
                        null
                    } else {
                        "Debes ingresar la hora de inicio"
                    },

                errorHoraFin =
                    if (!horaFinValida) {
                        "Debes ingresar la hora de término"
                    } else if (!duracionValida) {
                        "La sesión debe durar entre 30 y 60 minutos"
                    } else {
                        null
                    },

                errorObservador =
                    if (observadorValido) {
                        null
                    } else {
                        "Debes seleccionar un observador"
                    }
            )
        }

        return accesoValido &&
                fechaValida &&
                horaInicioValida &&
                horaFinValida &&
                observadorValido &&
                duracionValida
    }

    fun programarSesion() {

        if (_uiState.value.guardando) {
            return
        }

        if (!validarFormulario()) {
            return
        }

        val estado = _uiState.value

        val sesion = SesionObservacionEntity(
            acceso = estado.acceso,
            fecha = estado.fecha,
            horaInicio = estado.horaInicio,
            horaFin = estado.horaFin,
            observador = estado.observador
        )

        _uiState.update {
            it.copy(
                guardando = true,
                mensajeExito = null
            )
        }

        viewModelScope.launch {

            repository.insertarSesion(sesion)

            _uiState.update {
                it.copy(
                    acceso = "",
                    fecha = "",
                    horaInicio = "",
                    horaFin = "",
                    observador = "",
                    guardando = false,
                    mensajeExito = "Sesión programada correctamente"
                )
            }
        }
    }

    private fun convertirHoraAMinutos(hora: String): Int? {

        val partes = hora.split(":")

        if (partes.size != 2) {
            return null
        }

        val horas = partes[0].toIntOrNull()
        val minutos = partes[1].toIntOrNull()

        if (horas == null || minutos == null) {
            return null
        }

        return (horas * 60) + minutos
    }
}