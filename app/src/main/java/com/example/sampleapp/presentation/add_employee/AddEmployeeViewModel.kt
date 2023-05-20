package com.example.sampleapp.presentation.add_employee

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.EmployeeRepo
import com.example.sampleapp.data.database.Employee
import com.example.sampleapp.data.database.getDatabase
import kotlinx.coroutines.launch

class AddEmployeeViewModel(application: Application, context: Context, val employee: Employee?) :
    ViewModel() {
    private val database = getDatabase(application)
    private val employeesRepository = EmployeeRepo(context, database)

    fun saveEmployee(fullName: String, age: Int, experience: Int) {
        viewModelScope.launch {
            if (employee != null) {
                employeesRepository.updateEmployee(
                    Employee(
                        id = employee.id,
                        fullName = fullName,
                        age = age,
                        experience = experience
                    )
                )
            } else {
                employeesRepository.addEmployee(
                    Employee(
                        fullName = fullName,
                        age = age,
                        experience = experience
                    )
                )
            }
        }
    }
}