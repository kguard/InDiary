package com.kguard.indiary.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kguard.core.domain.model.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.FragmentDetailPersonBinding
import com.kguard.indiary.presentation.viewmodel.DetailPersonViewModel
import com.kguard.indiary.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPersonFragment : Fragment() {
    private lateinit var binding : FragmentDetailPersonBinding
    private val mainViewModel : MainViewModel by activityViewModels()
    private val viewModel: DetailPersonViewModel by viewModels()
    private var personId =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_detail_person,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.personId.observe(viewLifecycleOwner)
        {
            if (it != null) {
                personId = it
                viewModel.getPerson(it)
                viewModel.getMemoriesInPerson()
            }
        }

        viewModel.person.observe(viewLifecycleOwner){
            binding.tvDetailPersonName.text=it.name
            binding.tvDetailPersonBirth.text=it.birth
            binding.tvDetailPersonAge.text=viewModel.getAge(it.birth.toString())+"세"
            when(it.gender)
            {
                0->binding.tvDetailPersonGender.text="남"
                1->binding.tvDetailPersonGender.text="여"
                2->binding.tvDetailPersonGender.text="표기 안함"
            }
            binding.tvDetailPersonMemo.text=it.memo

        }
        binding.fbDeletePerson.setOnClickListener{
            DeletePersonDialogFragment(
                viewModel.person.value?: DomainPerson(), { person ->
                    viewModel.memories.observe(viewLifecycleOwner)
                    { memories ->
                        if (memories.find { it.person_id == person.person_id } == null)
                        {
                            Toast.makeText(context, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                            viewModel.deletePerson(person)
                            findNavController().popBackStack()
                        }
                        else
                        {
                            Toast.makeText(context, "삭제 할 수 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                {}
            ).show(childFragmentManager,"delete")
        }
        binding.fbUpdatePerson.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToUpdatePersonFragment(
                    personId
                )
            )
        }
    }


}