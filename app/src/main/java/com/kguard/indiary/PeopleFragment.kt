package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.kguard.indiary.adapter.PeopleAdapter
import com.kguard.indiary.adapter.TagAdapter
import com.kguard.indiary.databinding.FragmentPeopleBinding
import com.kguard.indiary.db.InDiaryDB
import com.kguard.indiary.viewmodel.PersonViewModel

class PeopleFragment : Fragment() {
    private val binding by lazy { FragmentPeopleBinding.inflate(layoutInflater) }

    private val viewModel by lazy {ViewModelProvider(this).get(PersonViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel=viewModel
        binding.lifecycleOwner=this

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val adapter=PeopleAdapter().apply { setHasStableIds(true) }
        binding.rvContent.adapter= adapter
        binding.rvTag.adapter=TagAdapter()
        viewModel.personAll?.observe(viewLifecycleOwner, Observer {
                person-> adapter.setData(person)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_peopleFragment_to_addPeopleFragment)
        }
    }


    /*fun setObserver()
    {
        viewModel.person.observe(this){
            viewModel.person.value?.let {  }
        }
    }*/


}