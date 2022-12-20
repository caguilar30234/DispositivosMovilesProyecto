package com.ufide.proyecto.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ufide.proyecto.model.Tarea

@Dao
interface TareaDao {

    //CRUD Create Read Update Delete
    @Query("SELECT * FROM TAREA")
    fun getTareas() : LiveData<List<Tarea>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTarea(tarea: Tarea)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateTarea(tarea: Tarea)

    @Delete
    suspend fun deleteTarea(tarea: Tarea)

}