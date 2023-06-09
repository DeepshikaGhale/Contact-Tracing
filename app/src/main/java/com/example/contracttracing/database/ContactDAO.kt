package com.example.contracttracing.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//this class holds the queries for the table
@Dao
interface ContactDAO {
    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    // the onConflict.IGNORE is used to ignore errors like:
    // even if the user enters duplicate data it will be stored in the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("UPDATE contact_table SET name=:name, number=:number WHERE id = :id")
    fun updateContact(name: String, number: String, id: Int)
}