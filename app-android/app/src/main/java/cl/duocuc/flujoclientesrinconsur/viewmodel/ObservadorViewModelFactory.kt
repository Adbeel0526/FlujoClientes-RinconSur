package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository

class ObservadorViewModelFactory(
    private val sesionRepository: SesionObservacionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                ObservadorViewModel::class.java
            )
        ) {

            @Suppress("UNCHECKED_CAST")
            return ObservadorViewModel(
                sesionRepository
            ) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconocido"
        )
    }
}