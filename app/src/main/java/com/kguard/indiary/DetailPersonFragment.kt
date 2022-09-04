package com.kguard.indiary


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.kguard.indiary.databinding.FragmentDetailPersonBinding
import com.kguard.indiary.viewmodel.PersonViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class DetailPersonFragment(personId: Int) : Fragment() {
    private val personId=personId
    private val binding by lazy { FragmentDetailPersonBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this).get(PersonViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val person= viewModel.getPerson(personId)?.value
        /*viewModel.personAll?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val person=it[personId-1]
            binding.tvDetailPersonName.text=person.name
            binding.tvDetailPersonBirth.text=person.birth
            binding.tvDetailPersonAge.text=getAge(person.birth.toString()).toString()
            when(person.gender)
            {
                0->binding.tvDetailPersonGender.text="남"
                1->binding.tvDetailPersonGender.text="여"
                2->binding.tvDetailPersonGender.text="표기 안함"
            }
            binding.tvDetailPersonMemo.text=person.memo
        })*/
    }
    fun getAge(string:String): Int {
        val now= LocalDate.now()
        val parsedBirthDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyyMMdd"))
        var age=now.minusYears(parsedBirthDate.year.toLong()).year
        if(parsedBirthDate.plusYears(age.toLong()).isAfter(now)){
            age -= 1
        }
        return age
    }
}