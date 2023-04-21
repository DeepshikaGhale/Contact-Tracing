package com.example.contracttracing.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//model class holding table attributes
@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(  autoGenerate = true) val id: Int?,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "number") val number: String
)
