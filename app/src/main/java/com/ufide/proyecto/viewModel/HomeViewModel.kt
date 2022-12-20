package com.ufide.proyecto.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.ufide.proyecto.data.TareaDatabase
import com.ufide.proyecto.model.Tarea
import com.ufide.proyecto.repository.TareaRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TareaRepository
    val getTareas : LiveData<List<Tarea>>

    init {
        val tareaDao = TareaDatabase.getDatabase(application).tareaDao()
        repository = TareaRepository(tareaDao)
        getTareas = repository.getTareas
    }

    fun guardaTarea(tarea: Tarea){
        viewModelScope.launch { repository.saveTarea(tarea) }
    }

    fun borraTarea(tarea: Tarea){
        viewModelScope.launch { repository.eraseTarea(tarea) }
    }
}