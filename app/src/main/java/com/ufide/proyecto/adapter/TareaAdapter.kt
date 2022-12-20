package com.ufide.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ufide.proyecto.databinding.FragmentAddTareaBinding
import com.ufide.proyecto.databinding.TareaFilaBinding
import com.ufide.proyecto.model.Tarea
import com.ufide.proyecto.ui.home.HomeFragmentDirections
import com.ufide.proyecto.ui.home.UpdateTareaFragmentDirections

class TareaAdapter: RecyclerView.Adapter<TareaAdapter.TareaViewHolder>(){

    private var listaTareas = emptyList<Tarea>()

    fun setTareas(tareas: List<Tarea>){
        this.listaTareas = tareas
        notifyDataSetChanged()
    }

    inner class TareaViewHolder(private val itemBinding: TareaFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(tarea: Tarea){
            var completa = tarea.completa
            var valor = "Incompleta"
            if(completa == true){
                valor = "Completa"
            }

            itemBinding.tvNombre.text = tarea.nombreTarea
            itemBinding.tvFechaEntrega.text = tarea.fechaEntrega
            itemBinding.tvDescripcionTarea.text = tarea.Description
            itemBinding.tvCompletaTarea.text = valor

            //Update
            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections.actionNavHomeToUpdateLugarFragment(tarea)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val itemBinding = TareaFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  TareaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tareaActual = listaTareas[position]
        holder.dibuja(tareaActual)
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }
    fun setData(tareas: List<Tarea>) {
        this.listaTareas = tareas
        notifyDataSetChanged()
    }

}