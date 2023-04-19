package com.example.contracttracing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //to go to main activity after some time
        Handler(Looper.myLooper()!!).postDelayed(
            {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            3000
        )
    }
}