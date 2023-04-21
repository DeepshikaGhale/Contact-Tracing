package com.example.contracttracing

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.contracttracing.database.Contact

class ContactAdapter(private val context: Context, val deleteContact: (contact: Contact)-> Boolean ,private val contactList: MutableList<Contact>) : BaseAdapter() {
    private lateinit var name: TextView
    private lateinit var contactNum: TextView
    private lateinit var delete: ImageButton
    private lateinit var edit: ImageButton

    override fun getCount(): Int {
        return contactList.size
    }

    override fun getItem(index: Int): Any {
        return index
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getView(position: Int, contactView: View?, parentView: ViewGroup?) : View? {
        var contactView = contactView
        contactView = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parentView, false)
        name = contactView.findViewById(R.id.contactName)
        contactNum = contactView.findViewById(R.id.contactNumber)
        delete = contactView.findViewById(R.id.deleteBtnId)
        edit = contactView.findViewById(R.id.editBtnId)

        name.text = contactList[position].name
        contactNum.text = contactList[position].number

        edit.setOnClickListener(){
            val intent = Intent(context, AddContact::class.java)
            intent.putExtra("id", contactList[position].id )
            intent.putExtra("name", contactList[position].name)
            intent.putExtra("number", contactList[position].number)

                parentView?.context?.startActivity(intent)
        }


        delete.setOnClickListener(){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Contact")
            builder.setMessage("Do you want to delete this contact?")
            builder.setPositiveButton(android.R.string.ok){
                dialog, which ->
                deleteContact(contactList[position])
                Toast.makeText(context, android.R.string.ok, Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.cancel){
                    dialog, which ->
                Toast.makeText(context, android.R.string.cancel, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        return contactView
    }
}