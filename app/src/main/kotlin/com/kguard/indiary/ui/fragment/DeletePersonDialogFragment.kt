package com.kguard.indiary.ui.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.kguard.core.domain.model.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.FragmentDeletePersonDialogBinding


class DeletePersonDialogFragment(
    val person: DomainPerson,
    val onDelete: (DomainPerson) -> Unit,
    val onCancel: () -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentDeletePersonDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_delete_person_dialog,container,false)
        binding.tvDeletePersonConfirm.setOnClickListener {
            onDelete(person)
            dismiss()

        }
        binding.tvDeletePersonCancel.setOnClickListener {
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