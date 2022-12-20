package com.ufide.proyecto.ui.home

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
import com.ufide.proyecto.R
import com.ufide.proyecto.databinding.FragmentAddTareaBinding
import com.ufide.proyecto.model.Tarea
import com.ufide.proyecto.viewModel.HomeViewModel
import java.util.Calendar


class AddTareaFragment : Fragment() {

    private var _binding: FragmentAddTareaBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAddTareaBinding.inflate(inflater,container,false)

        binding.btAddFecha.setOnClickListener(View.OnClickListener {
            var cal = Calendar.getInstance()
            var year = cal.get(Calendar.YEAR)
            var month = cal.get(Calendar.MONTH)
            var day = cal.get(Calendar.DAY_OF_MONTH)
            var datePickerDialog = DatePickerDialog(requireContext(),DatePickerDialog.THEME_HOLO_DARK,DatePickerDialog.OnDateSetListener{ datePicker: DatePicker, year: Int, month: Int, day: Int ->
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

        binding.btAgregaTarea.setOnClickListener{addTarea()}

        // Retorna la vista asociada
        return binding.root
    }

    private fun addTarea(){

        val nombreTarea = binding.etNombreTarea.text.toString()

        if(nombreTarea.isNotEmpty()){

            val descripcionTarea = binding.etDescripcionTarea.text.toString()
            val completaTarea = binding.valorCheck.text.toString().toBoolean()
            val fechaTarea = binding.etFechaTarea.text.toString()

            val tarea = Tarea(0,nombreTarea,fechaTarea,descripcionTarea,completaTarea,null)
            homeViewModel.guardaTarea(tarea)
            Toast.makeText(requireContext(),getText(R.string.ms_AddTarea), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addTareaFragment_to_nav_home)

        }else{
            Toast.makeText(requireContext(),getText(R.string.ms_FaltaNombre), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}