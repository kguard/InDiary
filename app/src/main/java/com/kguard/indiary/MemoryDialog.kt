package com.kguard.indiary

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kguard.indiary.adapter.AddWithAdapter
import com.kguard.indiary.databinding.DialogAddCharacterBinding
import com.kguard.indiary.databinding.DialogMemoryBinding
import com.kguard.indiary.util.Utils
import com.kguard.indiary.viewmodel.MainViewModel
import com.kguard.indiary.viewmodel.MemoryDialogViewModel
import com.kguard.indiary.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint


class MemoryDialog : DialogFragment() {
    private lateinit var binding: DialogMemoryBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onStart() {
        super.onStart()
        if(dialog != null && activity !=null && isAdded){
            val fullWidth= Utils.getScreenWidth(requireActivity()) * .85
            dialog?.window?.setLayout(fullWidth.toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.dialog_memory,container,false)
        val adapter= AddWithAdapter({mainViewModel.setPerson(it)},this)
        binding.rvAddWith.adapter=adapter
        mainViewModel.getPersons()
        mainViewModel.persons.observe(viewLifecycleOwner, Observer {
            adapter.setDate(it)
        })
        binding.rvAddWith.itemAnimator
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}