package com.ufide.proyecto.ui.home

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ufide.proyecto.R
import com.ufide.proyecto.databinding.FragmentUpdateTareaBinding
import com.ufide.proyecto.viewModel.HomeViewModel
import com.ufide.proyecto.model.Tarea
import java.util.*

class UpdateTareaFragment : Fragment() {
    private val args by navArgs<UpdateTareaFragmentArgs>()

    private var _binding: FragmentUpdateTareaBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentUpdateTareaBinding.inflate(inflater,container,false)


        binding.btAddFecha.setOnClickListener(View.OnClickListener {
            var cal = Calendar.getInstance()
            var year = cal.get(Calendar.YEAR)
            var month = cal.get(Calendar.MONTH)
            var day = cal.get(Calendar.DAY_OF_MONTH)
            var datePickerDialog = DatePickerDialog(requireContext(),
                DatePickerDialog.THEME_HOLO_DARK,
                DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, month: Int, day: Int ->
                binding.etFechaTarea.setText(""+day+"/"+month+"/"+year)
            }, year,month,day)
            datePickerDialog.show()
        })

        binding.chkCompleta.setOnClickListener(View.OnClickListener {
            if(binding.chkCompleta.isChecked)
            {
                binding.valorCheck.setText("true")
            }else{
                binding.valorCheck.setText("false")
            }
        })

        binding.btActualizaTarea.setOnClickListener{updateTarea()}
        binding.btEliminaTarea.setOnClickListener{deleteTarea()}
        binding.etNombreTarea.setText(args.tareaArg.nombreTarea)
        binding.etDescripcionTarea.setText(args.tareaArg.Description)
        binding.etFechaTarea.setText(args.tareaArg.fechaEntrega)
        binding.valorCheck.setText(args.tareaArg.completa.toString())

        return binding.root
    }

    private fun updateTarea(){
        val nombre = binding.etNombreTarea.text.toString()
        val descripcion = binding.etDescripcionTarea.text.toString()
        val fecha = binding.etFechaTarea.text.toString()
        val completa = binding.valorCheck.text.toString().toBoolean()

        if(nombre.isNotEmpty()){
            val tarea = Tarea(args.tareaArg.id,nombre,fecha,descripcion,completa,null)
            homeViewModel.guardaTarea(tarea)
            Toast.makeText(requireContext(),getString(R.string.ms_UpdateTarea), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateTareaFragment_to_nav_home)
        }else{
            Toast.makeText(requireContext(),getString(R.string.ms_FaltanValores), Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteTarea() {
        val nombre = binding.etNombreTarea.text.toString()
        val descripcion = binding.etDescripcionTarea.text.toString()
        val fecha = binding.etFechaTarea.text.toString()
        val completa = binding.valorCheck.text.toString().toBoolean()
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.msg_seguro_borrado)+"${args.tareaArg.nombreTarea}?")
        builder.setNegativeButton(getString(R.string.msg_no)) {_,_ -> }
        builder.setPositiveButton(getString(R.string.msg_si)) { _, _ ->
            if(nombre.isNotEmpty()){
                val tarea = Tarea(args.tareaArg.id,nombre,fecha,descripcion,completa,null)
                homeViewModel.borraTarea(tarea)
                Toast.makeText(requireContext(), getString(R.string.msg_tarea_deleted), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateTareaFragment_to_nav_home)
            }else{
                Toast.makeText(requireContext(),getString(R.string.ms_CantRemoveTarea),Toast.LENGTH_LONG).show()
            }
        }
        builder.create().show()
    }

}