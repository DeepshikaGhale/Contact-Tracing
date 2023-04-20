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

    //variable to hold the value for contact modal
    var contactName: String? = null
    var contactNumber: String? = null

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

        //get contacts data from last activity
        contactName= intent.getStringExtra("name")
        contactNumber = intent.getStringExtra("number")

        //set value if there is data from last activity
        setValue()

        Log.d("data-contact", "$contactName $contactNumber")

        binding.addContactBtn.setOnClickListener(){
            if(contactName == null && contactNumber == null){
                getValueFromView()
            }else{
                val contactData = ContactModel(contactName.toString(), contactNumber.toString())
                editContact(contactData)
            }
        }
    }

    //get the contact data from user
    private fun getValueFromView(){
        var name = binding.nameId.text.toString()
        var number = binding.phoneNumberId.text.toString()

        // field validation
        if(name.isNotBlank() && number.isNotBlank()){
            addContact(name, number)
            binding.nameId.text.clear()
            binding.phoneNumberId.text.clear()
        }else{
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addContact(name:String, number:String){
        var contact = ContactModel(name, number)
        contactList.contactList.add(contact)
        print(contactList.contactList.size)
        Log.d("length", contactList.contactList.size.toString())

        Toast.makeText(this, "Contact added successfully.", Toast.LENGTH_SHORT).show()
    }

    private fun editContact(contact: ContactModel){

    }

    private fun setValue(){
        if(contactName != null && contactNumber != null){
            binding.nameId.setText(contactName)
            binding.phoneNumberId.setText(contactNumber)
        }
    }
}