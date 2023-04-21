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
import androidx.lifecycle.ViewModelProvider
import com.example.contracttracing.database.AppDatabase
import com.example.contracttracing.database.Contact
import com.example.contracttracing.database.ContactViewModel
import com.example.contracttracing.databinding.ActivityAddContactBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AddContact : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var appDatabase: AppDatabase

    var contactList = ContactList()

    //variable to hold the value for contact modal
    var contactName: String? = null
    var contactNumber: String? = null
    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create database
        appDatabase = AppDatabase.getDatabase(this)

        //show back button
        var actionBar = supportActionBar

        if (actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add Contact"
        }

        //get contacts data from last activity

        contactName= intent.getStringExtra("name")
        contactNumber = intent.getStringExtra("number")
        id = intent.getIntExtra("id", 0)

        //set value if there is data from last activity
        setValue()

        binding.addContactBtn.setOnClickListener(){
            if(contactName == null && contactNumber == null){
                getValueFromView()
            }else{
                val contactData = Contact(id, contactName.toString(), contactNumber.toString())
                Log.d("data-contact", "$id, $contactName, $contactNumber")
                updateData(contactData)
            }
        }
    }

    //get the contact data from user
    private fun getValueFromView(){
        var name = binding.nameId.text.toString()
        var number = binding.phoneNumberId.text.toString()

        // field validation
        if(name.isNotBlank() && number.isNotBlank()){
            val contact = Contact(null, name, number)
            writeData(contact)
//            addContact(name, number)
            binding.nameId.text.clear()
            binding.phoneNumberId.text.clear()
        }else{
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setValue(){
        if(contactName != null && contactNumber != null){
            binding.nameId.setText(contactName)
            binding.phoneNumberId.setText(contactNumber)
        }
    }

    private fun writeData(contact: Contact){
        GlobalScope.launch (Dispatchers.IO){
            appDatabase.contactDao().insertContact(contact)
        }
        Toast.makeText(this, "Data has been successfully added.", Toast.LENGTH_SHORT).show()
    }

    private fun updateData(contact: Contact){
        var name = binding.nameId.text.toString()
        var number = binding.phoneNumberId.text.toString()

        if(name.isNotBlank() && number.isNotBlank()){

            GlobalScope.launch(Dispatchers.IO) {
                appDatabase.contactDao().updateContact(name, number, id.toString().toInt())
                finish()
            }
            Toast.makeText(this, "Data has been successfully updated.", Toast.LENGTH_SHORT).show()

            binding.nameId.text.clear()
            binding.phoneNumberId.text.clear()
        }else{
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }

    }
}