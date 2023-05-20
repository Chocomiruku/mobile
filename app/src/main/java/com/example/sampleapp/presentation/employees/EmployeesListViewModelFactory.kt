package com.example.sampleapp.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.presentation.employees.EmployeesListViewModel

class EmployeesViewModelFactory(
    private val application: Application,
    private val context: Context
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeesListViewModel::class.java)) {
            return EmployeesListViewModel(application, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}