package com.kguard.indiary.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kguard.core.model.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.FragmentUpdatePersonBinding
import com.kguard.indiary.presentation.viewmodel.UpdatePersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class UpdatePersonFragment() : Fragment() {
    private val args by navArgs<UpdatePersonFragmentArgs>()
    private lateinit var binding: FragmentUpdatePersonBinding
    private val viewModel: UpdatePersonViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_update_person, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = resources.getStringArray(R.array.GenderDetail)
        val spinnerAdapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        (binding.SpinnerMenu2.editText as? AutoCompleteTextView)?.setAdapter(spinnerAdapter)
        viewModel.getPerson(args.personId)
        viewModel.person.observe(viewLifecycleOwner) {
            binding.etUpdatePersonName.editText?.setText(it.name)
            binding.etUpdatePersonBirth.editText?.setText(it.birth)
            when (it.gender) {
                0 -> (binding.SpinnerMenu2.editText as AutoCompleteTextView).setText("남", false)
                1 -> (binding.SpinnerMenu2.editText as AutoCompleteTextView).setText("여", false)
                2 -> (binding.SpinnerMenu2.editText as AutoCompleteTextView).setText("표기 안함", false)
            }
            binding.etUpdatePersonMemo.setText(it.memo)
        }
        binding.tvUpdatePersonComplete.setOnClickListener {
            var person = DomainPerson(person_id = args.personId)
            val pattern = binding.etUpdatePersonBirth.editText?.text?.let { it1 ->
                Pattern.matches(
                    "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\$",
                    it1
                )
            }
            if (binding.etUpdatePersonName.editText?.text?.isEmpty() == true) {
                binding.etUpdatePersonBirth.isErrorEnabled = false
                binding.etUpdatePersonName.error = getString(R.string.NameEmptyError)
            } else if (pattern == false && binding.etUpdatePersonBirth.editText?.text?.isNotEmpty() == true) {
                binding.etUpdatePersonName.isErrorEnabled = false
                binding.etUpdatePersonBirth.error = getString(R.string.BirthError)
            } else {
                binding.etUpdatePersonName.isErrorEnabled = false
                binding.etUpdatePersonBirth.isErrorEnabled = false
                person.name = binding.etUpdatePersonName.editText?.text.toString()
                person.birth = binding.etUpdatePersonBirth.editText?.text.toString()
                person.memo = binding.etUpdatePersonMemo.text.toString()
                viewModel.person.observe(viewLifecycleOwner){
                    person.make=it.make
                }
                when (binding.SpinnerMenu2.editText?.text.toString()) {
                    "남" -> {
                        person.gender = 0
                    }
                    "여" -> {
                        person.gender = 1
                    }
                    "표기 안함" -> {
                        person.gender = 2
                    }
                }
                viewModel.updatePerson(person)
                findNavController().popBackStack()
            }
        }
    }


}