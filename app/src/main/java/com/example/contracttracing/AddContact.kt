package com.example.contracttracing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.contracttracing.databinding.ActivityAddContactBinding

class AddContact : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    var contactList = ContactList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addContactBtn.setOnClickListener(){
            var name = binding.nameId.text.toString()
            var number = binding.phoneNumberId.text.toString()

            if(name.isNotBlank() && number.isNotBlank()){
                addContact(name, number)
                name = ""
                number = ""
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

    }
}