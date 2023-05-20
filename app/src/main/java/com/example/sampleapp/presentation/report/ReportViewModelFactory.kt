package com.example.sampleapp.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.presentation.employees.EmployeesListViewModel
import com.example.sampleapp.presentation.report.ReportViewModel

class ReportViewModelFactory(
    private val application: Application,
    private val context: Context
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            return ReportViewModel(application, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}