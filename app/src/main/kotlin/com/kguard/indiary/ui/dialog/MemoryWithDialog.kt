package com.kguard.indiary.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kguard.indiary.R
import com.kguard.indiary.presentation.adapter.AddWithAdapter
import com.kguard.indiary.databinding.DialogMemoryWithBinding
import com.kguard.indiary.util.Utils
import com.kguard.indiary.presentation.viewmodel.MainViewModel


class MemoryWithDialog : DialogFragment() {
    private lateinit var binding: DialogMemoryWithBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onStart() {
        super.onStart()
        if(dialog != null && activity !=null && isAdded){
            val fullWidth= Utils.getScreenWidth(requireActivity()) * .9
            dialog?.window?.setLayout(fullWidth.toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
            //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.dialog_memory_with,container,false)
        val adapter= AddWithAdapter({mainViewModel.setPerson(it)},this)
        binding.rvAddWith.adapter=adapter
        mainViewModel.getPersons()
        mainViewModel.persons.observe(viewLifecycleOwner, Observer {
            adapter.setDate(it)
        })
        binding.rvAddWith.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
        binding.rvAddWith.itemAnimator
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}