package com.ufide.proyecto.repository

import androidx.lifecycle.LiveData
import com.ufide.proyecto.data.TareaDao
import com.ufide.proyecto.model.Tarea

class TareaRepository (private val tareaDao: TareaDao){
    suspend fun saveTarea(tarea: Tarea) {
        if (tarea.id == 0) {
            tareaDao.addTarea(tarea)
        } else {
            tareaDao.updateTarea(tarea)
        }
    }

        suspend fun eraseTarea(tarea : Tarea){
            tareaDao.deleteTarea(tarea)
        }

    val getTareas: LiveData<List<Tarea>> = tareaDao.getTareas()
}