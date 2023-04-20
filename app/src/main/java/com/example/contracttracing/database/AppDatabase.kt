package com.example.contracttracing.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase  : RoomDatabase(){
    abstract fun contactDao() : ContactDAO

    // using the singleton pattern:
    // to make sure that only one instance of the database is created on the app
    // so that when only function of the database will be used at a time
    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        //to get/create the database
        fun getDatabase(context: Context): AppDatabase{

            val tempInstance = INSTANCE
            //if database instance is already present
            if (tempInstance != null){
                return tempInstance
            }

            //synchronized is for executing the database operations synchronously
            synchronized(this){
                //if the instance is created for the first time
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" //name of the database
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}