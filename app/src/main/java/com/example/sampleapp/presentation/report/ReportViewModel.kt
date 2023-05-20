package com.example.sampleapp.presentation.report

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sampleapp.data.EmployeeRepo
import com.example.sampleapp.data.database.getDatabase

class ReportViewModel(application: Application, context: Context) : ViewModel() {
    private val database = getDatabase(application)
    private val employeesRepository = EmployeeRepo(context, database)

    val employees = employeesRepository.employees
}