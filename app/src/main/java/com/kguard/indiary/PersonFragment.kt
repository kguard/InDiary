package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.kguard.indiary.adapter.PersonAdapter
import com.kguard.indiary.adapter.TagAdapter
import com.kguard.indiary.databinding.FragmentPersonBinding
import com.kguard.indiary.viewmodel.PersonViewModel

class PersonFragment : Fragment() {
    private val binding by lazy { FragmentPersonBinding.inflate(layoutInflater) }
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
        /*lifecycleScope.launchWhenStarted {
            viewModel.personAll?.observe(viewLifecycleOwner, Observer {
                PeopleAdapter(it){
                    person -> viewModel.updatePerson(person)
                }.apply { setHasStableIds(true) }.also{adapter-> binding.rvContent.adapter=adapter }
            })
        }*/
        /*PeopleAdapter{
            person -> viewModel.updatePerson(person)
        }.apply { setHasStableIds(true) }.also {
            adapter -> binding.rvContent.adapter=adapter
        }.also {
            adapter ->
            viewModel.personAll?.observe(viewLifecycleOwner, Observer {
                    *//*person-> adapter.setData(person)*//*
            })
        }*/
        val adapter=PersonAdapter{
            it -> findNavController().navigate(PersonFragmentDirections.actionPersonFragmentToDetailFragment(it))
        }.apply { setHasStableIds(true) }
            .also { adpater ->binding.rvContent.adapter=adpater }
        viewModel.personAll?.observe(viewLifecycleOwner, Observer {
                person-> adapter.setData(person)
        })

        binding.rvTag.adapter=TagAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_personFragment_to_addPersonFragment)
        }
    }


    /*fun setObserver()
    {
        viewModel.person.observe(this){
            viewModel.person.value?.let {  }
        }
    }*/


}