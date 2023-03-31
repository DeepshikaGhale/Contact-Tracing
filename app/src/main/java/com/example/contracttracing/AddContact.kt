package com.example.contracttracing

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import com.example.contracttracing.databinding.ActivityAddContactBinding

class AddContact : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    var contactList = ContactList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //show back button
        var actionBar = supportActionBar

        if (actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add Contact"
        }

//        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//               this@AddContact.finish()
//            }
//        })

        binding.addContactBtn.setOnClickListener(){
            var name = binding.nameId.text.toString()
            var number = binding.phoneNumberId.text.toString()

            if(name.isNotBlank() && number.isNotBlank()){
                addContact(name, number)
                binding.nameId.text.clear()
                binding.phoneNumberId.text.clear()
            }else{
                Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun addContact(name:String, number:String){
        var contact = ContactModel(name, number)
        contactList.contactList.add(contact)
        print(contactList.contactList.size)
        Log.d("length", contactList.contactList.size.toString())

        Toast.makeText(this, "Contact added successfully.", Toast.LENGTH_SHORT).show()
    }
}