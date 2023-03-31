package com.example.contracttracing

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.contracttracing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var contacts = ContactList()
    var customContactListAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

       contacts.contactList.add(ContactModel("Deepshikha", "9808129230"))
       contacts.contactList.add(ContactModel("Deepshikha", "9808129230"))

        Log.d("length", contacts.contactList.size.toString())

        //show list in view
        //use custom array adapter and defines an array
        customContactListAdapter = ContactAdapter(this, contacts.contactList)
        binding.contactList.adapter = customContactListAdapter

        binding.addContact.setOnClickListener(){
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)

        }
    }


}