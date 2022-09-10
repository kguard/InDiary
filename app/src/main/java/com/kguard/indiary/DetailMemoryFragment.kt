package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import com.kguard.indiary.adapter.MemoryAdapter
import com.kguard.indiary.databinding.FragmentDetailMemory2Binding
import com.kguard.indiary.databinding.FragmentDetailMemoryBinding
import com.kguard.indiary.databinding.FragmentMemoryBinding
import com.kguard.indiary.util.ItemHelperImpl
import com.kguard.indiary.viewmodel.DetailMemoryViewModel
import com.kguard.indiary.viewmodel.MemoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailMemoryFragment(personId :Int) : Fragment() {
    private val person_id=personId
    private val binding by lazy { FragmentDetailMemoryBinding.inflate(layoutInflater) }
    private val viewModel: DetailMemoryViewModel by viewModels()
    private lateinit var adapter: MemoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter= MemoryAdapter({
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToDetailMemory2Fragment(it))
        },{
            viewModel.deleteMemory(it)
        }).apply { setHasStableIds(true) }
        binding.rvDetailMemory.adapter=adapter
        ItemHelperImpl(adapter).also {
            ItemTouchHelper(it).apply {
                this.attachToRecyclerView(binding.rvDetailMemory)
            }
        }
        viewModel.getMemory(person_id)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.memory.collectLatest {
                adapter.submitList(it)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }


}