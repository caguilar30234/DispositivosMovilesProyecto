package com.ufide.proyecto

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat



class SplashScreenActivity : AppCompatActivity() {
    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_splash_screen)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        WindowInsetsCompat.Type.systemBars()

        Handler(Looper.getMainLooper()).postDelayed({

                var intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()

        }, 3000)

    }
}