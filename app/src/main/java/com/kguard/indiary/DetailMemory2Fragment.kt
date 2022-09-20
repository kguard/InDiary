package com.kguard.indiary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.adapter.PhotoAdapter
import com.kguard.indiary.databinding.FragmentDetailMemory2Binding
import com.kguard.indiary.databinding.FragmentDetailPersonBinding
import com.kguard.indiary.viewmodel.DetailMemory2ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMemory2Fragment : Fragment() {
    private val args by navArgs<DetailMemory2FragmentArgs>()
    private lateinit var binding :FragmentDetailMemory2Binding
    private val viewModel: DetailMemory2ViewModel by viewModels()
    private var adapter = PhotoAdapter {}.apply { setHasStableIds(true) }
    private var memory = DomainMemory()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_detail_memory2,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMemory(args.memoryId)
        viewModel.memory.observe(viewLifecycleOwner, Observer { it ->
            viewModel.clearPhoto()
            Log.d("==============Detail2", "onViewCreated:Detail2 memory")
            binding.tvDetailMemory2Title.text = it.title
            binding.tvDetailMemory2Date.text = it.date
            binding.tvDetailMemory2Content.text = it.content
            binding.rvDetailMemory2.adapter=adapter
            Log.d("--Detail2", "onViewCreated: ${it.imageList}")
            it.imageList.forEach {
                if (it != null) { viewModel.setPhoto(it) }
            }
            Log.d("-!!!-Detail2", "onViewCreated: ${viewModel.photos.value}")

            it.person_id?.let { it1 -> viewModel.getPerson(it1) }
            viewModel.person.observe(viewLifecycleOwner, Observer {
                binding.tvDetailMemory2With.text = it.name
            })
            memory = it
        })

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            Log.d("==Detail2", "onViewCreated: ${photos} ")
            adapter.updatePhoto(photos)
        }


            binding.fbDeleteMemory.setOnClickListener {
                viewModel.deleteMemory(memory)
                findNavController().popBackStack()
            }

            binding.fbUpdateMemory.setOnClickListener {
                viewModel.clearPhoto()
                findNavController().navigate(
                    DetailMemory2FragmentDirections.actionDetailMemory2FragmentToUpdateMemoryFragment(args.memoryId)
                )
            }


    }


}