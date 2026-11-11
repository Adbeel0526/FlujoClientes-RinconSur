package cl.duocuc.flujoclientesrinconsur.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "registro_observacion",

    foreignKeys = [
        ForeignKey(
            entity = SesionObservacionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sesionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index(
            value = ["sesionId"],
            unique = true
        )
    ]
)
data class RegistroObservacionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sesionId: Int,
    val transito: Int,
    val visita: Int,
    val noDeterminado: Int
)