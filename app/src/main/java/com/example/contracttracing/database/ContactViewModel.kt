package com.example.contracttracing.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {
    val readAllContactData: LiveData<List<Contact>>
    private val contactRepository: ContactRepository

    init {
        val contactDAO = AppDatabase.getDatabase(application).contactDao()
        contactRepository = ContactRepository(contactDAO)
        readAllContactData = contactRepository.readlAllDBData
    }
}