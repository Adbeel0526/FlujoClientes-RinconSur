package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duocuc.flujoclientesrinconsur.data.repository.SesionObservacionRepository

class SesionObservacionViewModelFactory(
    private val repository: SesionObservacionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SesionObservacionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SesionObservacionViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}