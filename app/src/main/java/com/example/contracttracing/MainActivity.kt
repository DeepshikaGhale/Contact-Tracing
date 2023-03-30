package com.example.contracttracing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.contracttracing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val contacts = ContactList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

        //use arrayAdapter and defines an array
        val arrayAdapter: ArrayAdapter<*>

       contacts.contactList.add(ContactModel("Deepshika", "9808129230"))
       Log.d("size", contacts.contactList.toString())

        //show list in view
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts.contactList)
        binding.contactList.adapter = arrayAdapter

        binding.addContact.setOnClickListener(){
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
            Toast.makeText(this, "This is test message", Toast.LENGTH_SHORT).show()
        }



//        binding.contactList
    }


}