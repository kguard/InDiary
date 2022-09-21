package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.databinding.FragmentDeleteMemoryDialogBinding
import com.kguard.indiary.databinding.FragmentDeletePersonDialogBinding

class DeleteMemoryDialogFragment(
    val memory: DomainMemory,
    val onDelete: (DomainMemory) -> Unit,
    val onCancel: () -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentDeleteMemoryDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_delete_memory_dialog,
            container,
            false
        )
        binding.tvDeleteMemoryConfirm.setOnClickListener {
            onDelete(memory)
            dismiss()
        }
        binding.tvDeleteMemoryCancel.setOnClickListener {
            onCancel()
            dismiss()
        }
        return binding.root
    }

}