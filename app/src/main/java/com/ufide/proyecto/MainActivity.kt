package com.ufide.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ufide.proyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Firebase Authentication
    private lateinit var auth : FirebaseAuth

    //Llamado a objetro XML al que pertenece este activity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicia autenticacion desde Firebase
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Click on register
        binding.btnRegister.setOnClickListener{registrar()}

        //Click on login
        binding.btnLogin.setOnClickListener{login()}

    }

    private fun registrar(){

        //"binding" llama a los elementos del XML y asigna su valor a una constante
        val email = binding.etUsuario.text.toString()
        val clave = binding.etPassword.text.toString()

        //Registro hacia Firebase
        auth.createUserWithEmailAndPassword(email,clave).addOnCompleteListener(this){
            task->if(task.isSuccessful){
                val user = auth.currentUser
                cargaPantalla(user)
            }
            else{
                Toast.makeText(baseContext, "Fallo al registrarse: ${task.exception.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun cargaPantalla(user: FirebaseUser?){
        if(user != null){

            //Si el usuario no es nulo iniciar actividad "Principal"
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    private fun login(){


        val email = binding.etUsuario.text.toString()
        val clave = binding.etPassword.text.toString()

        //Autenticar usuario
        // "auth" contiene los diferentes tipos de autenticación
        auth.signInWithEmailAndPassword(email,clave).addOnCompleteListener{
            result->if(result.isSuccessful){
                val user = auth.currentUser
                cargaPantalla(user)
            }else{
            Toast.makeText(baseContext, getText(R.string.LoginError), Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cargaPantalla(user)
    }
}