package cl.duocuc.flujoclientesrinconsur.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.duocuc.flujoclientesrinconsur.data.local.dao.RegistroObservacionDao
import cl.duocuc.flujoclientesrinconsur.data.local.dao.SesionObservacionDao
import cl.duocuc.flujoclientesrinconsur.data.local.entity.RegistroObservacionEntity
import cl.duocuc.flujoclientesrinconsur.data.local.entity.SesionObservacionEntity

@Database(
    entities = [
        SesionObservacionEntity::class,
        RegistroObservacionEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sesionObservacionDao(): SesionObservacionDao

    abstract fun registroObservacionDao(): RegistroObservacionDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtenerBaseDatos(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "flujo_clientes_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instancia

                instancia
            }
        }
    }
}