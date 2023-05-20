package com.example.sampleapp.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fullName: String,
    val age: Int,
    val experience: Int,
) : Parcelable {
    override fun toString(): String {
        return "ФИО: $fullName\n" +
                "Возраст: $age\n" +
                "Опыт работы: $experience"
    }
}
