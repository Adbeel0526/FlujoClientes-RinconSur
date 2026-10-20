package cl.duocuc.flujoclientesrinconsur.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duocuc.flujoclientesrinconsur.data.repository.RegistroObservacionRepository

class RegistroObservacionViewModelFactory(
    private val repository: RegistroObservacionRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(RegistroObservacionViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return RegistroObservacionViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}