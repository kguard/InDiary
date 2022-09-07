package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.databinding.FragmentUpdateMemoryBinding
import com.kguard.indiary.viewmodel.UpdateMemoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateMemoryFragment : Fragment() {
    private val args by navArgs<UpdateMemoryFragmentArgs>()
    private val binding by lazy { FragmentUpdateMemoryBinding.inflate(layoutInflater) }
    private val viewModel : UpdateMemoryViewModel by viewModels()
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
        val memory=DomainMemory(args.memoryId,"","","","","","","","","")
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("날짜를 설정해주세요")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                ).build()
        viewModel.getMemory(args.memoryId)
        viewModel.memory.observe(viewLifecycleOwner, Observer {
            binding.etUpdateMemoryTitle.editText?.setText(it.title)
            binding.etUpdateMemoryWith.editText?.setText(it.with)
            binding.tvUpdateDate.text=it.date
            binding.etUpdateMemoryContent.setText(it.content)
        })
        binding.btUpdateMemoryDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager,"datePicker")
            dateRangePicker.addOnPositiveButtonClickListener {
                memory.date=dateRangePicker.headerText
                binding.tvUpdateDate.text=memory.date
            }
        }
        binding.btUpdateMemoryComplete.setOnClickListener {
            memory.title=binding.etUpdateMemoryTitle.editText?.text.toString()
            memory.content=binding.etUpdateMemoryContent.text.toString()
            memory.with=binding.etUpdateMemoryWith.editText?.text.toString()
            memory.date=binding.tvUpdateDate.text.toString()
            viewModel.updateMemory(memory)
            findNavController().popBackStack()
        }
    }


}