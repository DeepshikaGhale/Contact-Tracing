package com.example.contracttracing

import android.R
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contracttracing.database.AppDatabase
import com.example.contracttracing.database.Contact
import com.example.contracttracing.database.ContactViewModel
import com.example.contracttracing.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //set up database
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var appDatabase: AppDatabase

    var contacts : MutableList<Contact> = mutableListOf()

    private var customContactListAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

        //connect to APP DATABASE
        appDatabase = AppDatabase.getDatabase(this)

        //connect to view model
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.readAllContactData.observe(this, Observer { contacts ->

            //show list in view
            //use custom array adapter and defines an array
            customContactListAdapter = ContactAdapter(this,
                fun(contact:Contact): Boolean{
                    deleteContact(contact)
                    return true
                },
                contacts.toMutableList())

            binding.contactList.adapter = customContactListAdapter

        })

        //when user presses on floating action button
        binding.addContact.setOnClickListener(){
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }

        binding.contactList.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
                showContactDetails(contacts[position])
        }
    }

    //show when user taps on list item
    private fun showContactDetails(contact: Contact){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Name: ${contact.name}")
        builder.setMessage("Phone Number: ${contact.number}")
        builder.show()
    }


    //called when user clicks on the delete button
    private fun deleteContact(contact: Contact){
        GlobalScope.launch {
            appDatabase.contactDao().deleteContact(contact)
        }
        Toast.makeText(this, "Successfully Deleted!!", Toast.LENGTH_SHORT).show()
    }

}