package com.ufide.proyecto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufide.proyecto.R
import com.ufide.proyecto.adapter.TareaAdapter
import com.ufide.proyecto.databinding.FragmentHomeBinding
import com.ufide.proyecto.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btAddTarea.setOnClickListener{
            findNavController().navigate(R.id.action_nav_home_to_addTareaFragment)
        }

        //Lista Tareas
        val tareaAdapter = TareaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = tareaAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.getTareas.observe(viewLifecycleOwner){
            tareas -> tareaAdapter.setTareas(tareas)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}