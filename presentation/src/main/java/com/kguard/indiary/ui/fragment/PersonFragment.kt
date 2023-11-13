package com.kguard.indiary.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.kguard.indiary.R
import com.kguard.indiary.presentation.adapter.PersonAdapter
import com.kguard.indiary.databinding.FragmentPersonBinding
import com.kguard.indiary.util.ItemHelperImpl
import com.kguard.indiary.presentation.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PersonFragment : Fragment() {
    private lateinit var binding: FragmentPersonBinding
    private val viewModel: PersonViewModel by viewModels()
//    private val mainViewModel: MainViewModel by activityViewModels()
    private val adapter = PersonAdapter(
        { id ->
            findNavController().navigate(
                com.kguard.indiary.PersonFragmentDirections.actionPersonFragmentToDetailFragment(
                    id
                )
            )
        },
        { person ->
            DeletePersonDialogFragment(
                person, { delete ->
                    viewModel.memories.observe(viewLifecycleOwner, Observer { memories ->
                        if (memories.find { it.person_id == delete.person_id } == null) {
                            Toast.makeText(context, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                            viewModel.deletePerson(delete)
                        }
                        else{
                            Toast.makeText(context, "삭제 할 수 없습니다.", Toast.LENGTH_SHORT).show()
                            viewModel.clearPerson()
                            viewModel.getPersons()
                        }
                    })

                },
                {
                    viewModel.clearPerson()
                    viewModel.getPersons()
                }
            ).show(childFragmentManager, "delete")
        },
        { person ->
            viewModel.updatePerson(person.copy(favorite = !person.favorite))
        })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        viewModel.getMemoriesInPerson()

        binding.rvPersonContent.adapter = this.adapter
        ItemHelperImpl(adapter).also {
            ItemTouchHelper(it).apply {
                this.attachToRecyclerView(binding.rvPersonContent)
            }
        }

        binding.tvDiary.setOnLongClickListener {
            Intent(context, OssLicensesMenuActivity::class.java).also {
                OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
                startActivity(it)
            }
            return@setOnLongClickListener false
        }


        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_personFragment_to_addPersonFragment)
        }
        viewModel.getPersons()
//        binding.rvTag.adapter = TagAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.persons.collectLatest {
                adapter.submitList(it)
            }
        }
    }
}