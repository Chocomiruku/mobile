package com.example.sampleapp.presentation.report

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.databinding.ActivityReportBinding
import com.example.sampleapp.presentation.ReportViewModelFactory
import com.example.sampleapp.util.formatToString

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding

    private val viewModel: ReportViewModel by lazy {
        ViewModelProvider(
            this,
            ReportViewModelFactory(application, this)
        )[ReportViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReportBinding.inflate(layoutInflater)

        viewModel.employees.observe(this) { employees ->
            val filteredEmployees = employees.filter { employee ->
                employee.age > 30
            }
            binding.employeesText.text = filteredEmployees.formatToString()
        }

        setContentView(binding.root)
    }
}