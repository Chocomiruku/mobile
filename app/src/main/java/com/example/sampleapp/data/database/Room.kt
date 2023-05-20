package com.example.sampleapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao
}

private lateinit var INSTANCE: EmployeeDatabase

fun getDatabase(context: Context): EmployeeDatabase {
    synchronized(EmployeeDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                EmployeeDatabase::class.java,
                "employee_db"
            ).build()
        }
    }
    return INSTANCE
}