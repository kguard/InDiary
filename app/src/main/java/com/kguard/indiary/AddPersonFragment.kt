package com.kguard.indiary


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import com.kguard.domain.domain.DomainCharacter
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.adapter.AddCharacterAdapter
import com.kguard.indiary.databinding.FragmentAddPersonBinding
import com.kguard.indiary.viewmodel.AddPersonViewModel
import com.kguard.indiary.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

import java.time.LocalDate

@AndroidEntryPoint
class AddPersonFragment : Fragment() {
    private val binding by lazy { FragmentAddPersonBinding.inflate(layoutInflater) }
    private val viewModel : AddPersonViewModel by viewModels()
//    private val mainViewModel: MainViewModel by activityViewModels()
//    private lateinit var adapter: AddCharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items=resources.getStringArray(R.array.GenderDetail)

        val spinnerAdapter =
            context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_dropdown_item,items) }
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
            var person: DomainPerson =DomainPerson()
            if(binding.etAddPersonBirth.editText?.text?.length != 8)
            {
                Toast.makeText(context, "생년월일을 8자로 써주세요", Toast.LENGTH_SHORT).show()
            }
            else {
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