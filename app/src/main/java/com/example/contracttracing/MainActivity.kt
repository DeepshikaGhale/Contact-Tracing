package com.example.contracttracing

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contracttracing.database.Contact
import com.example.contracttracing.database.ContactViewModel
import com.example.contracttracing.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var appDatabase: AppDatabase
    private lateinit var contactViewModel: ContactViewModel

//    var contacts = ContactList()
    var contacts : MutableList<Contact> = mutableListOf()
    var test = false

    private var customContactListAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

        //create database
//        appDatabase = AppDatabase.getDatabase(this)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.readAllContactData.observe(this, Observer { contacts ->
            //show list in view
            //use custom array adapter and defines an array
            customContactListAdapter = ContactAdapter(this,
                fun(): Boolean {
                    Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                    return true
                }, fun(): Boolean{
                    Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show()
                    return true
            }, contacts.toMutableList())
            binding.contactList.adapter = customContactListAdapter

            binding.addContact.setOnClickListener(){
                val intent = Intent(this, AddContact::class.java)
                startActivity(intent)
            }

            binding.contactList.onItemClickListener = AdapterView.OnItemClickListener{
                    parent, view, position, id ->

                onClickEdit(contacts[id.toInt()], id.toInt(), )
            } })

            Log.d("contacts-size", contacts.size.toString())


            Log.d("length", contacts.size.toString())




    }

    //called when user clicks on the list item
    fun onClickEdit(contact: Contact, key: Int){
        val intent = Intent(this, AddContact::class.java)
        intent.putExtra("id", key )
        intent.putExtra("name", contact.name)
        intent.putExtra("number", contact.number)
        startActivity(intent)
    }

    private suspend fun displayContactsData(contacts: MutableList<Contact>){
            withContext(Dispatchers.Main){

            }
    }

    private fun readData(){
        lateinit var dbContacts: MutableList<Contact>


    }


}