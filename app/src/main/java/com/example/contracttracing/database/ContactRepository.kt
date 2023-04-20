package com.example.contracttracing.database

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDAO : ContactDAO) {
    val readlAllDBData: LiveData<List<Contact>> = contactDAO.getAll()
}