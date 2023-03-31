package com.example.contracttracing

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.contracttracing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var contacts = ContactList()
    var customContactListAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

       contacts.contactList.add(ContactModel("Deepshikha Ghale", "123-234-45678"))
       contacts.contactList.add(ContactModel("Daniel Gurung", "321-234-7654"))
       contacts.contactList.add(ContactModel("Ram Prasad", "098-231-5673"))

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