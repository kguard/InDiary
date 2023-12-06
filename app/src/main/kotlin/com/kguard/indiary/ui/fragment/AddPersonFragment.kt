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
import com.kguard.indiary.R
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.databinding.FragmentAddPersonBinding
import com.kguard.indiary.presentation.viewmodel.AddPersonViewModel

import dagger.hilt.android.AndroidEntryPoint

import java.time.LocalDate
import java.util.regex.Pattern

@AndroidEntryPoint
class AddPersonFragment : Fragment() {
    private lateinit var binding: FragmentAddPersonBinding
    private val viewModel: AddPersonViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_person, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = resources.getStringArray(R.array.GenderDetail)

        val spinnerAdapter =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        (binding.SpinnerMenu.editText as? AutoCompleteTextView)?.setAdapter(spinnerAdapter)

//        binding.btAddPersonPlus.setOnClickListener{
//            findNavController().navigate(R.id.action_addPersonFragment_to_addCharacterDialog)
//        }

//        adapter=AddCharacterAdapter()
//        binding.rvAddPerson.adapter=adapter
//        mainViewModel.customCharacter.observe(viewLifecycleOwner){
//            adapter.addData(it)
//            Log.d(javaClass.simpleName, "onViewCreated: $it")
//        }

        binding.btAddPersonComplete.setOnClickListener {
            var person: DomainPerson = DomainPerson()
            val pattern = binding.etAddPersonBirth.editText?.text?.let { it1 ->
                Pattern.matches(
                    "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\$",
                    it1
                )
            }
            if (binding.etAddPersonName.editText?.text?.isEmpty() == true) {
                binding.etAddPersonBirth.isErrorEnabled = false
                binding.etAddPersonName.error = getString(R.string.NameEmptyError)
            } else if (pattern == false && binding.etAddPersonBirth.editText?.text?.isNotEmpty() == true) {

                binding.etAddPersonName.isErrorEnabled = false
                binding.etAddPersonBirth.error = getString(R.string.BirthError)
            } else {
                binding.etAddPersonName.isErrorEnabled = false
                binding.etAddPersonBirth.isErrorEnabled = false
                person.name = binding.etAddPersonName.editText?.text.toString()
                person.birth = binding.etAddPersonBirth.editText?.text.toString()
                person.make = LocalDate.now().toString()
                person.memo = binding.etAddPersonMemo.text.toString()
                when (binding.SpinnerMenu.editText?.text.toString()) {
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
                viewModel.insertPerson(person)
                findNavController().popBackStack()
            }
        }

    }

}