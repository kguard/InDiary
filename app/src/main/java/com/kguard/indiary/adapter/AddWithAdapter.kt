package com.kguard.indiary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.databinding.ItemCustomCharacterBinding
import com.kguard.indiary.databinding.ItemRecyclerWithBinding

class AddWithAdapter(val onclick : (DomainPerson) ->Unit, val dialog: DialogFragment ):RecyclerView.Adapter<AddWithAdapter.AddWithViewHolder>(){
    private var persons:List<DomainPerson> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddWithViewHolder {
        val binding=ItemRecyclerWithBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddWithViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddWithViewHolder, position: Int) {
        holder.setName(persons[position])

    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class AddWithViewHolder(val binding: ItemRecyclerWithBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setName(person: DomainPerson)
        {
            binding.root.setOnClickListener {
                onclick(person)
                dialog.dismiss()
            }
            binding.tvWith.text=person.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDate(person:List<DomainPerson>)
    {
        this.persons=person
        notifyDataSetChanged()
    }
}