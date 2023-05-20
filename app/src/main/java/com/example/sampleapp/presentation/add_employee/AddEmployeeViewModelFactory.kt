package com.example.sampleapp.presentation.add_employee

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.data.database.Employee
import com.example.sampleapp.presentation.employees.EmployeesListViewModel

class AddEmployeeViewModelFactory(
    private val application: Application,
    private val context: Context,
    private val employee: Employee?
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEmployeeViewModel::class.java)) {
            return AddEmployeeViewModel(application, context, employee) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}