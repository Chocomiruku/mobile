package com.example.sampleapp.presentation.add_employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sampleapp.databinding.FragmentAddEmployeeBinding
import com.example.sampleapp.databinding.FragmentEmployeesListBinding

class AddEmployeeFragment : Fragment() {
    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddEmployeeViewModel by lazy {
        val activity = requireNotNull(this.activity) { }
        ViewModelProvider(
            this,
            AddEmployeeViewModelFactory(
                activity.application,
                requireContext(),
                AddEmployeeFragmentArgs.fromBundle(requireArguments()).employee
            )
        )[AddEmployeeViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)

        AddEmployeeFragmentArgs.fromBundle(requireArguments()).employee?.let { employee ->
            binding.nameEditText.setText(employee.fullName)
            binding.ageEditText.setText(employee.age.toString())
            binding.experienceEditText.setText(employee.experience.toString())
        }

        binding.saveBtn.setOnClickListener {
            if (infoFilled()) {
                viewModel.saveEmployee(
                    binding.nameEditText.text.toString(),
                    binding.ageEditText.text.toString().toInt(),
                    binding.experienceEditText.text.toString().toInt()
                )

                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun infoFilled(): Boolean {
        return !binding.nameEditText.text.isNullOrBlank() && !binding.ageEditText.text.isNullOrBlank() && !binding.experienceEditText.text.isNullOrBlank()
    }
}