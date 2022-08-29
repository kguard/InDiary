package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kguard.indiary.databinding.FragmentMemoryBinding
import com.kguard.indiary.databinding.FragmentPeopleBinding

class MemoryFragment : Fragment() {
    private val binding by lazy { FragmentMemoryBinding.inflate(layoutInflater) }
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
        super.onViewCreated(view, savedInstanceState)
        binding.btAddMemory.setOnClickListener {
            findNavController().navigate(R.id.action_memoryFragment_to_addMemoryFragment)
        }

    }

}