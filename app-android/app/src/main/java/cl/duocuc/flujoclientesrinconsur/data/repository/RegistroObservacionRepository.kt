package cl.duocuc.flujoclientesrinconsur.data.repository

import cl.duocuc.flujoclientesrinconsur.data.local.dao.RegistroObservacionDao
import cl.duocuc.flujoclientesrinconsur.data.local.entity.RegistroObservacionEntity
import kotlinx.coroutines.flow.Flow

class RegistroObservacionRepository(
    private val registroObservacionDao: RegistroObservacionDao
) {

    suspend fun insertarRegistro(
        registro: RegistroObservacionEntity
    ) {
        registroObservacionDao.insertarRegistro(registro)
    }

    fun obtenerRegistros(): Flow<List<RegistroObservacionEntity>> {
        return registroObservacionDao.obtenerRegistros()
    }
}