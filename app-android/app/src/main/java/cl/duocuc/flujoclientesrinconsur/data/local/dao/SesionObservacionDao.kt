package cl.duocuc.flujoclientesrinconsur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cl.duocuc.flujoclientesrinconsur.data.local.entity.SesionObservacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SesionObservacionDao {

    @Insert
    suspend fun insertarSesion(
        sesion: SesionObservacionEntity
    )

    @Query("SELECT * FROM sesion_observacion ORDER BY id DESC")
    fun obtenerSesiones(): Flow<List<SesionObservacionEntity>>

    @Query("SELECT * FROM sesion_observacion WHERE id = :id LIMIT 1")
    suspend fun obtenerSesionPorId(
        id: Int
    ): SesionObservacionEntity?

    @Update
    suspend fun actualizarSesion(
        sesion: SesionObservacionEntity
    )
}