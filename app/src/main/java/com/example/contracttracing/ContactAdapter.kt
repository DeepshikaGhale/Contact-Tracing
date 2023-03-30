package com.example.contracttracing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class ContactAdapter(private val context: Context, private val mutableList: java.util.ArrayList<ContactModel>) : BaseAdapter() {
    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, contactView: View?, parentView: ViewGroup?) : View? {
        var contactView = contactView


        return contactView
    }
}