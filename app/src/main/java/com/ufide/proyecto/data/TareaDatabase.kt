package com.ufide.proyecto.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ufide.proyecto.model.Tarea

@Database(entities=[Tarea::class], version = 1, exportSchema = false)
abstract class TareaDatabase : RoomDatabase() {

    abstract fun tareaDao() : TareaDao

    companion object {
        @Volatile
        private var INSTANCE: TareaDatabase? = null
        fun getDatabase(context: android.content.Context): TareaDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TareaDatabase::class.java,
                    "tarea_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
