package com.example.contracttracing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contracttracing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

        binding.addContact.setOnClickListener(){
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
            Toast.makeText(this, "This is test message", Toast.LENGTH_SHORT).show()
        }
    }


}