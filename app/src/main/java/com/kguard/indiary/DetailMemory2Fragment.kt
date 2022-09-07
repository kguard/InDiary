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
import com.kguard.indiary.databinding.FragmentDetailMemory2Binding
import com.kguard.indiary.databinding.FragmentDetailPersonBinding
import com.kguard.indiary.viewmodel.DetailMemory2ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMemory2Fragment : Fragment() {
    private val args by navArgs<DetailMemory2FragmentArgs>()
    private val binding by lazy { FragmentDetailMemory2Binding.inflate(layoutInflater) }
    private val viewModel:DetailMemory2ViewModel by viewModels()
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
        viewModel.getMemory(args.memoryId)
        viewModel.memory.observe(viewLifecycleOwner, Observer {
            binding.tvDetailMemory2Title.text=it.title
            binding.tvDetailMemory2With.text=it.with
            binding.tvDetailMemory2Date.text=it.date
            binding.tvDetailMemory2Content.text=it.content
            val memory=it
            binding.fbDeleteMemory.setOnClickListener{
                viewModel.deleteMemory(memory)
                findNavController().popBackStack()
            }
            binding.fbUpdateMemory.setOnClickListener {
                findNavController().navigate(DetailMemory2FragmentDirections.actionDetailMemory2FragmentToUpdateMemoryFragment(args.memoryId))
            }
        })
    }


}