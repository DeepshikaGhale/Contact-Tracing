package com.example.contracttracing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ContactAdapter(private val context: Context, private val contactList: MutableList<ContactModel>) : BaseAdapter() {
    private lateinit var name: TextView
    private lateinit var contactNum: TextView

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

        name.text = contactList[position].name
        contactNum.text = contactList[position].number

        return contactView
    }
}