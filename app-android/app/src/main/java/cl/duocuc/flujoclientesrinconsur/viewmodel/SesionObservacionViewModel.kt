package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import cl.duocuc.flujoclientesrinconsur.model.SesionObservacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SesionObservacionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SesionObservacionUiState())

    val uiState: StateFlow<SesionObservacionUiState> = _uiState

    fun onAccesoCambia(valor: String) {
        _uiState.update {
            it.copy(
                acceso = valor,
                errorAcceso = null
            )
        }
    }

    fun onFechaCambia(valor: String) {
        _uiState.update {
            it.copy(
                fecha = valor,
                errorFecha = null
            )
        }
    }

    fun onHoraInicioCambia(valor: String) {
        _uiState.update {
            it.copy(
                horaInicio = valor,
                errorHoraInicio = null
            )
        }
    }

    fun onHoraFinCambia(valor: String) {
        _uiState.update {
            it.copy(
                horaFin = valor,
                errorHoraFin = null
            )
        }
    }

    fun onObservadorCambia(valor: String) {
        _uiState.update {
            it.copy(
                observador = valor,
                errorObservador = null
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
                errorAcceso = if (accesoValido) null else "Debes seleccionar un acceso",
                errorFecha = if (fechaValida) null else "Debes ingresar una fecha",
                errorHoraInicio = if (horaInicioValida) null else "Debes ingresar la hora de inicio",
                errorHoraFin = if (!horaFinValida) {
                    "Debes ingresar la hora de término"
                } else if (!duracionValida) {
                    "La sesión debe durar entre 30 y 60 minutos"
                } else {
                    null
                },
                errorObservador = if (observadorValido) null else "Debes seleccionar un observador"
            )
        }

        return accesoValido &&
                fechaValida &&
                horaInicioValida &&
                horaFinValida &&
                observadorValido &&
                duracionValida
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