package com.example.contracttracing

import android.R
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
import com.example.contracttracing.database.Contact
import com.example.contracttracing.database.ContactViewModel
import com.example.contracttracing.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    var contacts : MutableList<Contact> = mutableListOf()

    private var customContactListAdapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding.root)

        //connect to view model
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.readAllContactData.observe(this, Observer { contacts ->
            //show list in view
            //use custom array adapter and defines an array
            customContactListAdapter = ContactAdapter(this,
                contacts.toMutableList())
            binding.contactList.adapter = customContactListAdapter

            binding.addContact.setOnClickListener(){
                val intent = Intent(this, AddContact::class.java)
                startActivity(intent)
            }

            binding.contactList.onItemClickListener = AdapterView.OnItemClickListener{
                    parent, view, position, id ->

                view.findViewById<ImageButton>(R.id.edit).setOnClickListener(){
                    onClickEdit(contacts[id.toInt()], id.toInt())
                    Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
                }

                var viewModel = parent.adapter.getView(position, view, parent)


                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
//                view.findViewById<ImageButton>(R.id.delete).setOnClickListener(){
//                    onClickEdit(contacts[id.toInt()], id.toInt())
//                    Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
//                }
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

}