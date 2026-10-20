package cl.duocuc.flujoclientesrinconsur.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registro_observacion")
data class RegistroObservacionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val transito: Int,
    val visita: Int,
    val noDeterminado: Int
)