package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository

class RegistroObservacionViewModelFactory(
    private val registroRepository: RegistroObservacionRepository,
    private val sesionRepository: SesionObservacionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                RegistroObservacionViewModel::class.java
            )
        ) {

            @Suppress("UNCHECKED_CAST")
            return RegistroObservacionViewModel(
                registroRepository,
                sesionRepository
            ) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconocido"
        )
    }
}