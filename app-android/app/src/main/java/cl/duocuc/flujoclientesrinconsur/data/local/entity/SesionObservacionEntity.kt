package cl.duocuc.flujoclientesrinconsur.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sesion_observacion")
data class SesionObservacionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val acceso: String,
    val fecha: String,
    val horaInicio: String,
    val horaFin: String,
    val observador: String,
    val estado: String = "PROGRAMADA"
)