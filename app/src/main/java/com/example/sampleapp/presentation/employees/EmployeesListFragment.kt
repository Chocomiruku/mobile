package com.example.sampleapp.presentation.employees

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sampleapp.databinding.FragmentEmployeesListBinding
import com.example.sampleapp.presentation.EmployeesViewModelFactory
import com.example.sampleapp.presentation.report.ReportActivity

class EmployeesListFragment : Fragment() {
    private var _binding: FragmentEmployeesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployeesListViewModel by lazy {
        val activity = requireNotNull(this.activity) { }
        ViewModelProvider(
            this,
            EmployeesViewModelFactory(activity.application, requireContext())
        )[EmployeesListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeesListBinding.inflate(inflater, container, false)

        viewModel.employees.observe(viewLifecycleOwner) { employees ->
            val adapter = EmployeeAdapter { employee ->
                findNavController().navigate(
                    EmployeesListFragmentDirections.actionEmployeesListFragmentToAddEmployeeFragment()
                        .setEmployee(employee)
                )
            }

            binding.employeesList.adapter = adapter
            adapter.submitList(employees)
        }

        binding.addEmployeeBtn.setOnClickListener {
            findNavController().navigate(EmployeesListFragmentDirections.actionEmployeesListFragmentToAddEmployeeFragment())
        }

        binding.showReportBtn.setOnClickListener {
            val intent = Intent(requireActivity(), ReportActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}