package com.kguard.indiary


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels

import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.databinding.FragmentAddPersonBinding
import com.kguard.indiary.viewmodel.AddPersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AddPersonFragment : Fragment() {
    private val binding by lazy { FragmentAddPersonBinding.inflate(layoutInflater) }
    private val viewModel : AddPersonViewModel by viewModels()
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
        binding.include.tvToolbarName.text=getString(R.string.AddPersonPage)
        val items=resources.getStringArray(R.array.GenderDetail)

        val spinnerAdapter =
            context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_dropdown_item,items) }
        binding.spinner.adapter=spinnerAdapter

        binding.tvAddPersonComplete.setOnClickListener {
            var person: DomainPerson =DomainPerson(0,"김경호","",0,"연습",LocalDate.now().toString(),false)

            person.name=binding.etAddPersonName.text.toString()
            person.birth =binding.etAddPersonBirth.text.toString()
            person.memo=binding.etAddPersonMemo.text.toString()

            binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position)
                    {
                        0-> person.gender=0
                        1-> person.gender=1
                        2-> person.gender=2
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    person.gender=2
                }

            }
            viewModel.insertPerson(person)
            findNavController().popBackStack()
        }

    }

}