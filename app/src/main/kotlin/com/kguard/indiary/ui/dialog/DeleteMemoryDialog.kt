package com.kguard.indiary.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.R
import com.kguard.indiary.databinding.FragmentDeleteMemoryDialogBinding

class DeleteMemoryDialog(
    val memory: DomainMemory,
    val onDelete: (DomainMemory) -> Unit,
    val onCancel: () -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentDeleteMemoryDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onCancel()
    }

}