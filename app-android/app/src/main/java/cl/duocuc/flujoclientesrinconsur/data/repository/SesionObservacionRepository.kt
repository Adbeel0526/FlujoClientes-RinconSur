package cl.duocuc.flujoclientesrinconsur.data.repository

import cl.duocuc.flujoclientesrinconsur.data.local.dao.SesionObservacionDao
import cl.duocuc.flujoclientesrinconsur.data.local.entity.SesionObservacionEntity
import kotlinx.coroutines.flow.Flow

class SesionObservacionRepository(
    private val sesionObservacionDao: SesionObservacionDao
) {

    fun obtenerSesiones(): Flow<List<SesionObservacionEntity>> {
        return sesionObservacionDao.obtenerSesiones()
    }

    suspend fun obtenerSesionPorId(
        id: Int
    ): SesionObservacionEntity? {
        return sesionObservacionDao.obtenerSesionPorId(id)
    }

    suspend fun insertarSesion(
        sesion: SesionObservacionEntity
    ) {
        sesionObservacionDao.insertarSesion(sesion)
    }

    suspend fun actualizarSesion(
        sesion: SesionObservacionEntity
    ) {
        sesionObservacionDao.actualizarSesion(sesion)
    }
}