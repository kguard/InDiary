package com.kguard.indiary

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.kguard.indiary.databinding.FragmentQuitDialogBinding

class QuitDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentQuitDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=
            DataBindingUtil.inflate(inflater,R.layout.fragment_quit_dialog,container,false)
        binding.tvQuitConfirm.setOnClickListener {
            activity?.finish()
        }
        binding.tvQuitCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}