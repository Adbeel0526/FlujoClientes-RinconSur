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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SesionObservacionViewModel(
    private val repository: SesionObservacionRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            SesionObservacionUiState()
        )

    val uiState: StateFlow<SesionObservacionUiState> =
        _uiState

    fun onAccesoCambia(
        valor: String
    ) {

        _uiState.update {
            it.copy(
                acceso = valor,
                errorAcceso = null,
                mensajeExito = null
            )
        }
    }

    fun onFechaCambia(
        valor: String
    ) {

        _uiState.update {
            it.copy(
                fecha = valor,
                errorFecha = null,
                mensajeExito = null
            )
        }
    }

    fun onHoraInicioCambia(
        valor: String
    ) {

        _uiState.update {
            it.copy(
                horaInicio = valor,
                errorHoraInicio = null,
                errorHoraFin = null,
                mensajeExito = null
            )
        }
    }

    fun onHoraFinCambia(
        valor: String
    ) {

        _uiState.update {
            it.copy(
                horaFin = valor,
                errorHoraFin = null,
                mensajeExito = null
            )
        }
    }

    fun onObservadorCambia(
        valor: String
    ) {

        _uiState.update {
            it.copy(
                observador = valor,
                errorObservador = null,
                mensajeExito = null
            )
        }
    }

    fun programarSesion() {

        if (_uiState.value.guardando) {
            return
        }

        if (!validarFormulario()) {
            return
        }

        val estado =
            _uiState.value

        val sesion =
            SesionObservacionEntity(
                acceso = estado.acceso,
                fecha = estado.fecha,
                horaInicio = estado.horaInicio,
                horaFin = estado.horaFin,
                observador = estado.observador,
                estado = "PROGRAMADA"
            )

        _uiState.update {
            it.copy(
                guardando = true,
                mensajeExito = null
            )
        }

        viewModelScope.launch {

            try {

                repository.insertarSesion(
                    sesion
                )

                _uiState.update {
                    it.copy(
                        acceso = "",
                        fecha = "",
                        horaInicio = "",
                        horaFin = "",
                        observador = "",
                        guardando = false,
                        mensajeExito =
                            "Sesión programada correctamente"
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        guardando = false
                    )
                }
            }
        }
    }

    private fun validarFormulario(): Boolean {

        val estado =
            _uiState.value

        val accesoValido =
            validarAcceso(
                estado.acceso
            )

        val fechaValida =
            validarFecha(
                estado.fecha
            )

        val horaInicioMinutos =
            convertirHoraAMinutos(
                estado.horaInicio
            )

        val horaFinMinutos =
            convertirHoraAMinutos(
                estado.horaFin
            )

        val horaInicioValida =
            horaInicioMinutos != null

        val horaFinValida =
            horaFinMinutos != null

        val duracionValida =
            horaInicioMinutos != null &&
                    horaFinMinutos != null &&
                    (
                            horaFinMinutos -
                                    horaInicioMinutos
                            ) in 30..60

        val observadorValido =
            estado.observador.isNotBlank()

        _uiState.update {

            it.copy(

                errorAcceso =
                    if (accesoValido) {
                        null
                    } else {
                        "El acceso debe ser 1, 2, 3 o 4"
                    },

                errorFecha =
                    obtenerErrorFecha(
                        estado.fecha
                    ),

                errorHoraInicio =
                    if (horaInicioValida) {
                        null
                    } else {
                        "Ingresa una hora válida en formato HH:mm"
                    },

                errorHoraFin =
                    when {

                        !horaFinValida ->
                            "Ingresa una hora válida en formato HH:mm"

                        !duracionValida ->
                            "La sesión debe durar entre 30 y 60 minutos"

                        else ->
                            null
                    },

                errorObservador =
                    if (observadorValido) {
                        null
                    } else {
                        "Debes ingresar un observador"
                    }
            )
        }

        return accesoValido &&
                fechaValida &&
                horaInicioValida &&
                horaFinValida &&
                duracionValida &&
                observadorValido
    }

    private fun validarAcceso(
        acceso: String
    ): Boolean {

        return acceso in setOf(
            "1",
            "2",
            "3",
            "4"
        )
    }

    private fun validarFecha(
        fecha: String
    ): Boolean {

        if (fecha.isBlank()) {
            return false
        }

        val formato =
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

        formato.isLenient = false

        return try {

            val fechaIngresada =
                formato.parse(
                    fecha
                ) ?: return false

            val hoyTexto =
                formato.format(
                    Date()
                )

            val hoy =
                formato.parse(
                    hoyTexto
                ) ?: return false

            !fechaIngresada.after(
                hoy
            )

        } catch (e: Exception) {

            false
        }
    }

    private fun obtenerErrorFecha(
        fecha: String
    ): String? {

        if (fecha.isBlank()) {
            return "Debes ingresar una fecha"
        }

        val formato =
            SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

        formato.isLenient = false

        val fechaIngresada =
            try {

                formato.parse(
                    fecha
                )

            } catch (e: Exception) {

                null
            }

        if (fechaIngresada == null) {

            return "Ingresa una fecha válida en formato dd/MM/yyyy"
        }

        val hoyTexto =
            formato.format(
                Date()
            )

        val hoy =
            formato.parse(
                hoyTexto
            )

        if (
            hoy != null &&
            fechaIngresada.after(
                hoy
            )
        ) {

            return "La fecha no puede ser posterior a hoy"
        }

        return null
    }

    private fun convertirHoraAMinutos(
        hora: String
    ): Int? {

        if (
            !hora.matches(
                Regex(
                    "^([01]\\d|2[0-3]):[0-5]\\d$"
                )
            )
        ) {

            return null
        }

        val partes =
            hora.split(":")

        val horas =
            partes[0].toInt()

        val minutos =
            partes[1].toInt()

        return (
                horas * 60
                ) + minutos
    }
}