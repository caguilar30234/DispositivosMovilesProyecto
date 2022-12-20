package com.ufide.proyecto
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import com.ufide.proyecto.databinding.ActivityIntroBinding
import com.ufide.proyecto.databinding.ActivityPrincipalBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowInsetsCompat.Type.systemBars()

        binding.btnInicio.setOnClickListener{
            var intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}