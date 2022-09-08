package com.kguard.indiary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.kguard.indiary.adapter.MemoryAdapter

import com.kguard.indiary.databinding.FragmentMemoryBinding
import com.kguard.indiary.databinding.FragmentPersonBinding
import com.kguard.indiary.viewmodel.MemoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoryFragment : Fragment() {
    private val binding by lazy { FragmentMemoryBinding.inflate(layoutInflater) }
    private val viewModel: MemoryViewModel by viewModels()
    private lateinit var adapter: MemoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter=MemoryAdapter{
            findNavController().navigate(MemoryFragmentDirections.actionMemoryFragmentToDetailMemory2Fragment(it))
        }
        binding.rvMemoryContent.adapter=adapter
        viewModel.getMemories()
        viewModel.memory.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    }

}