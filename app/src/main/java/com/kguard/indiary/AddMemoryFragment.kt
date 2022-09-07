package com.kguard.indiary

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.kguard.data.local.entity.Memory
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.databinding.FragmentAddMemoryBinding
import com.kguard.indiary.databinding.FragmentAddPersonBinding
import com.kguard.indiary.databinding.FragmentMemoryBinding
import com.kguard.indiary.viewmodel.AddMemoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class AddMemoryFragment : Fragment() {
    private val binding by lazy { FragmentAddMemoryBinding.inflate(layoutInflater) }
    private val viewModel : AddMemoryViewModel by viewModels()
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
        var memory = DomainMemory(0,"추억","","","","","","","","")
        super.onViewCreated(view, savedInstanceState)
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("날짜를 설정해주세요")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                ).build()
        binding.btAddMemoryDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager,"datePicker")
            dateRangePicker.addOnPositiveButtonClickListener {
                memory.date=dateRangePicker.headerText
                binding.tvAddDate.text=memory.date
            }
        }
        binding.btAddMemoryComplete.setOnClickListener {
            memory.title=binding.etAddMemoryTitle.editText?.text.toString()
            memory.content=binding.etAddMemoryContent.text.toString()
            memory.with=binding.etAddMemoryWith.editText?.text.toString()
            viewModel.insertMemory(memory)
            findNavController().popBackStack()
        }
    }

}