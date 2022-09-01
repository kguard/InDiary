package com.kguard.indiary


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.PopUpToBuilder
import androidx.navigation.fragment.findNavController
import com.kguard.indiary.databinding.FragmentAddPersonBinding
import com.kguard.indiary.databinding.FragmentPersonBinding
import com.kguard.indiary.db.Person
import com.kguard.indiary.db.Tag
import com.kguard.indiary.viewmodel.PersonViewModel
import java.time.LocalDate
import java.util.Objects.toString


class AddPersonFragment : Fragment() {
    private val binding by lazy { FragmentAddPersonBinding.inflate(layoutInflater) }
    private val viewModel by lazy {ViewModelProvider(this).get(PersonViewModel::class.java)}
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
            var person1: Person =Person(0,"김경호","",0,"연습",LocalDate.now().toString(),false,
                emptyList(), emptyList())
            var person: Person =Person()

            person.name=binding.etAddPersonName.text.toString()
            person.birth =binding.etAddPersonBirth.text.toString()
            person.memo=binding.etAddPersonMemo.text.toString()
            person.make=LocalDate.now().toString()
            person.Tag= emptyList()
            person.Character= emptyList()
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