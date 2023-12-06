package com.kguard.indiary.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.kguard.indiary.R
import com.kguard.indiary.presentation.adapter.MemoryAdapter

import com.kguard.indiary.databinding.FragmentMemoryBinding
import com.kguard.indiary.util.ItemHelperImpl
import com.kguard.indiary.presentation.viewmodel.MemoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MemoryFragment : Fragment() {
    private lateinit var binding: FragmentMemoryBinding
    private val viewModel: MemoryViewModel by viewModels()
    private val adapter = MemoryAdapter({
        findNavController().navigate(
            MemoryFragmentDirections.actionMemoryFragmentToDetailMemory2Fragment(
                it
            )
        )
    }, { memory ->
        DeleteMemoryDialogFragment(
            memory, {
                Toast.makeText(context, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                viewModel.deleteMemory(it)
            },
            {
                viewModel.clearMemories()
                viewModel.getMemories()
            }
        ).show(childFragmentManager, "delete")
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memory, container, false)

        binding.rvMemoryContent.adapter = adapter

        ItemHelperImpl(adapter).also {
            ItemTouchHelper(it).apply {
                this.attachToRecyclerView(binding.rvMemoryContent)
            }
        }
        binding.tvDiary.setOnLongClickListener {
            Intent(context, OssLicensesMenuActivity::class.java).also {
                OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
                startActivity(it)
            }
            return@setOnLongClickListener false
        }
        binding.btAddMemory.setOnClickListener {
            findNavController().navigate(R.id.action_memoryFragment_to_addMemoryFragment)
        }
        viewModel.getMemories()
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