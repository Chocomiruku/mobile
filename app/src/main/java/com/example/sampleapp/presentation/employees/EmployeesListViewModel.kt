package com.example.sampleapp.presentation.employees

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.EmployeeRepo
import com.example.sampleapp.data.database.getDatabase
import kotlinx.coroutines.launch

class EmployeesListViewModel(application: Application, context: Context) : ViewModel() {
    private val database = getDatabase(application)
    private val employeesRepository = EmployeeRepo(context, database)

    val employees = employeesRepository.employees

    init {
        viewModelScope.launch {
            employeesRepository.getEmployees()
        }
    }
}