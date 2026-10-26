package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository

class SupervisorViewModelFactory(
    private val sesionRepository: SesionObservacionRepository,
    private val registroRepository: RegistroObservacionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SupervisorViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return SupervisorViewModel(
                sesionRepository,
                registroRepository
            ) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}