package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.databinding.FragmentDetailPersonBinding
import com.kguard.indiary.databinding.FragmentUpdatePersonBinding
import com.kguard.indiary.viewmodel.UpdatePersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class UpdatePersonFragment()  : Fragment() {
    private val args by navArgs<UpdatePersonFragmentArgs>()
    private val binding by lazy { FragmentUpdatePersonBinding.inflate(layoutInflater) }
    private val viewModel :UpdatePersonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPerson(args.personId)
        viewModel.person.observe(viewLifecycleOwner, Observer {
            binding.etUpdatePersonName.editText?.setText(it.name)
            binding.etUpdatePersonBirth.editText?.setText(it.birth)
            when(it.gender){
                0->binding.SpinnerMenu2.editText?.setText("남")
                1->binding.SpinnerMenu2.editText?.setText("여")
                2->binding.SpinnerMenu2.editText?.setText("표기 안함")
            }
            binding.etUpdatePersonMemo.setText(it.memo)
        })
        binding.tvUpdatePersonComplete.setOnClickListener {
            var person: DomainPerson =
                DomainPerson(args.personId,"김경호","",0,"연습", LocalDate.now().toString(),false)
            person.name=binding.etUpdatePersonName.editText?.text.toString()
            person.birth =binding.etUpdatePersonBirth.editText?.text.toString()
            person.memo=binding.etUpdatePersonMemo.text.toString()
            when(binding.SpinnerMenu2.editText?.text.toString())
            {
                "남" -> {
                    person.gender=0
                }
                "여" -> {
                    person.gender=1
                }
                "표기 안함" -> {
                    person.gender=2
                }
            }
            viewModel.updatePerson(person)
            findNavController().popBackStack()
        }
    }


}