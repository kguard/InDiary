package com.kguard.indiary


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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
    private val binding by lazy { FragmentDetailPersonBinding.inflate(layoutInflater) }
    private val viewModel:DetailPersonViewModel by viewModels()
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
        val person= viewModel.getPerson(personId)
        viewModel.person.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.tvDetailPersonName.text=it.name
            binding.tvDetailPersonBirth.text=it.birth
            binding.tvDetailPersonAge.text=viewModel.getAge(it.birth.toString()).toString()
            when(it.gender)
            {
                0->binding.tvDetailPersonGender.text="남"
                1->binding.tvDetailPersonGender.text="여"
                2->binding.tvDetailPersonGender.text="표기 안함"
            }
            binding.tvDetailPersonMemo.text=it.memo
        })
    }

}