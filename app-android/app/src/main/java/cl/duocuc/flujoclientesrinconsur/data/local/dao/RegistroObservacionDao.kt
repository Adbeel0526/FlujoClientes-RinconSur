package cl.duocuc.flujoclientesrinconsur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cl.duocuc.flujoclientesrinconsur.data.local.entity.RegistroObservacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroObservacionDao {

    @Insert
    suspend fun insertarRegistro(
        registro: RegistroObservacionEntity
    )

    @Query("SELECT * FROM registro_observacion ORDER BY id DESC")
    fun obtenerRegistros(): Flow<List<RegistroObservacionEntity>>
}