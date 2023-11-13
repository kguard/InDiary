package com.kguard.indiary.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.kguard.indiary.R
import com.kguard.indiary.presentation.adapter.MemoryAdapter
import com.kguard.indiary.databinding.FragmentDetailMemoryBinding
import com.kguard.indiary.util.ItemHelperImpl
import com.kguard.indiary.presentation.viewmodel.DetailMemoryViewModel
import com.kguard.indiary.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailMemoryFragment : Fragment() {
    private lateinit var binding: FragmentDetailMemoryBinding
    private val viewModel: DetailMemoryViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private var personId =0
    private val adapter = MemoryAdapter({
        findNavController().navigate(
            DetailFragmentDirections.actionDetailFragmentToDetailMemory2Fragment(
                it
            )
        )
    }, { memory ->
        DeleteMemoryDialogFragment(
            memory,{
                Toast.makeText(context, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                viewModel.deleteMemory(it, this.personId)
            },
            {
                viewModel.clearMemories()
                viewModel.getMemory(this.personId)
            }
        ).show(childFragmentManager,"delete")
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail_memory, container, false)
        mainViewModel.personId.observe(viewLifecycleOwner)
        {
            if (it != null) {
                personId = it
                viewModel.getMemory(personId)
            }
        }
        binding.rvDetailMemory.adapter = adapter
        ItemHelperImpl(adapter).also {
            ItemTouchHelper(it).apply {
                this.attachToRecyclerView(binding.rvDetailMemory)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.memory.collectLatest {
                adapter.submitList(it)
            }
        }
    }

}