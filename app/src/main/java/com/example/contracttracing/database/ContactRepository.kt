package com.example.contracttracing.database

import androidx.lifecycle.LiveData

//created to access the queries method of the DAO class
class ContactRepository(private val contactDAO : ContactDAO) {
    //here live data acts as an observer for the changes shown in the database
    val readlAllDBData: LiveData<List<Contact>> = contactDAO.getAll()
}