package com.kguard.indiary


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.databinding.FragmentDetailPersonBinding
import com.kguard.indiary.viewmodel.DetailPersonViewModel
import com.kguard.indiary.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class DetailPersonFragment(personId: Int) : Fragment() {
    private val personId=personId
    private lateinit var binding : FragmentDetailPersonBinding
    private val viewModel:DetailPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_detail_person,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPerson(personId)
        viewModel.person.observe(viewLifecycleOwner){
            binding.tvDetailPersonName.text=it.name
            binding.tvDetailPersonBirth.text=it.birth
            binding.tvDetailPersonAge.text=viewModel.getAge(it.birth.toString())+"세"
            when(it.gender)
            {
                0->binding.tvDetailPersonGender.text="남"
                1->binding.tvDetailPersonGender.text="여"
                2->binding.tvDetailPersonGender.text="표기 안함"
            }
            binding.tvDetailPersonMemo.text=it.memo

        }
        binding.fbDeletePerson.setOnClickListener{
            DeletePersonDialogFragment(
                viewModel.person.value?: DomainPerson(), {
                    viewModel.deletePerson(it)
                    findNavController().popBackStack()
                },
                {}
            ).show(childFragmentManager,"delete")
        }
        binding.fbUpdatePerson.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToUpdatePersonFragment(personId))
        }
    }

}