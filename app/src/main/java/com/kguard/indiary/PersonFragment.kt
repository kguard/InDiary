package com.kguard.indiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.kguard.indiary.adapter.PersonAdapter
import com.kguard.indiary.adapter.TagAdapter
import com.kguard.indiary.databinding.FragmentPersonBinding
import com.kguard.indiary.util.PersonItemHelperImpl
import com.kguard.indiary.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PersonFragment : Fragment() {
    private val binding by lazy { FragmentPersonBinding.inflate(layoutInflater) }
    private val viewModel : PersonViewModel by viewModels()
    private lateinit var adapter: PersonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel=viewModel
        binding.lifecycleOwner=this


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        adapter=PersonAdapter{
            findNavController().navigate(PersonFragmentDirections.actionPersonFragmentToDetailFragment(it))
        }.apply { setHasStableIds(true) }
        binding.rvPersonContent.adapter=adapter
        PersonItemHelperImpl(adapter).also {
            ItemTouchHelper(it).apply {
                this.attachToRecyclerView(binding.rvPersonContent)
            }
        }
//        viewModel.getPersons()
//        viewModel.persons.observe(viewLifecycleOwner, Observer {
//            adapter.setData(it)
//        })


        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.persons.collectLatest{
                adapter.submitList(it)
            }
        }
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