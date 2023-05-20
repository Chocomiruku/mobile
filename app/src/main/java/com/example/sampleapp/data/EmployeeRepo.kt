package com.example.sampleapp.data

import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.LiveData
import com.example.sampleapp.data.database.Employee
import com.example.sampleapp.data.database.EmployeeDatabase
import com.example.sampleapp.util.readFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepo(private val context: Context, private val database: EmployeeDatabase) {

    val employees: LiveData<List<Employee>> =
        database.employeeDao.getEmployees()

    suspend fun getEmployees() {
        withContext(Dispatchers.IO) {
            val employees = getEmployeesFromJson(context)
            database.employeeDao.insertAll(employees)
        }
    }

    suspend fun addEmployee(employee: Employee) {
        withContext(Dispatchers.IO) {
            database.employeeDao.insert(employee)
        }
    }

    suspend fun updateEmployee(employee: Employee) {
        withContext(Dispatchers.IO) {
            database.employeeDao.update(employee)
        }
    }

    private fun getEmployeesFromJson(context: Context): List<Employee> {
        val jsonString = context.assets.readFile("employees.json")
        val typeToken = object : TypeToken<List<Employee>>() {}.type
        return Gson().fromJson(jsonString, typeToken)
    }

    private fun AssetManager.readFile(fileName: String) = open(fileName)
        .bufferedReader()
        .use { it.readText() }
}