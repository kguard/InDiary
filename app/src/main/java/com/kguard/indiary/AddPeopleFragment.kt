package com.kguard.indiary


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.PopUpToBuilder
import androidx.navigation.fragment.findNavController
import com.kguard.indiary.databinding.FragmentAddPeopleBinding
import com.kguard.indiary.databinding.FragmentPeopleBinding
import com.kguard.indiary.db.Person
import com.kguard.indiary.db.Tag
import com.kguard.indiary.viewmodel.PersonViewModel
import java.time.LocalDate
import java.util.Objects.toString


class AddPeopleFragment : Fragment() {
    private val binding by lazy { FragmentAddPeopleBinding.inflate(layoutInflater) }
    private val viewModel by lazy {ViewModelProvider(this).get(PersonViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.tvToolbarName.text=getString(R.string.AddPeoplePage)
        binding.tvComplete.setOnClickListener {
            var person= Person(0,"김경호","991101",0,"연습",LocalDate.now().toString(),false,
                emptyList(), emptyList())
            viewModel.insertPerson(person)
            findNavController().popBackStack()
        }

    }

}