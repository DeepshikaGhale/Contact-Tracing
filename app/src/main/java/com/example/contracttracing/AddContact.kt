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

        //action bar
        //show back button
        var actionBar = supportActionBar

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title ="Add Contact"
        }

        //create database
        appDatabase = AppDatabase.getDatabase(this)

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
            //validates the number
            if (validateNumber(number)){
                writeData(contact)
                binding.nameId.text.clear()
                binding.phoneNumberId.text.clear()
            }

        }else{
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setValue(){
        if(contactName != null && contactNumber != null){
            binding.nameId.setText(contactName)
            binding.phoneNumberId.setText(contactNumber)
            //update button name
            binding.addContactBtn.text = "Update Contact"
        }
    }

    private fun writeData(contact: Contact){
        GlobalScope.launch (Dispatchers.IO){
            appDatabase.contactDao().insertContact(contact)
            finish() // closes the add contact screen after data is added
        }
        Toast.makeText(this, "Data has been successfully added.", Toast.LENGTH_SHORT).show()
    }

    private fun updateData(contact: Contact){
        var name = binding.nameId.text.toString()
        var number = binding.phoneNumberId.text.toString()

        if(name.isNotBlank() && number.isNotBlank()){
            //validates the number
            if (validateNumber(number)){
                GlobalScope.launch(Dispatchers.IO) {
                    appDatabase.contactDao().updateContact(name, number, id.toString().toInt())
                    finish()
                }
                Toast.makeText(this, "Data has been successfully updated.", Toast.LENGTH_SHORT).show()

                binding.nameId.text.clear()
                binding.phoneNumberId.text.clear()
            }

        }else{
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }

    }

    //validate phone number data
    private fun validateNumber(number: String): Boolean{
        if(number.length < 10 || number.length > 10){
            Toast.makeText(this, "Phone Number should of 10 digits.", Toast.LENGTH_SHORT).show()
            return  true
        }
        return  false
    }
}