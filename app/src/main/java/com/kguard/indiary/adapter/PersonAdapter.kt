package com.kguard.indiary.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.ItemRecyclerPersonBinding
import com.kguard.indiary.util.ItemHelperInterface


class PersonAdapter(
    val onClick: (Int) -> Unit,
    val onDelete: (DomainPerson) -> Unit,
    val onFavorite:(DomainPerson) -> Unit
):ListAdapter<DomainPerson, PersonAdapter.PersonViewHolder>(diffUtil),ItemHelperInterface{

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<DomainPerson>() {
            override fun areItemsTheSame(
                oldItem: DomainPerson,
                newItem: DomainPerson
            ): Boolean  = oldItem.person_id == newItem.person_id

            override fun areContentsTheSame(
                oldItem: DomainPerson,
                newItem: DomainPerson
            ): Boolean = oldItem.favorite == newItem.favorite

        }
    }
   // private var person:List<DomainPerson> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding= ItemRecyclerPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setItem(getItem(position))

    }

    inner class PersonViewHolder(private val binding: ItemRecyclerPersonBinding) :RecyclerView.ViewHolder(binding.root){
        fun setItem(domainPerson: DomainPerson){
            binding.tvPeopleName.text= domainPerson.name
            binding.tvMemoryDate.text=domainPerson.make
            if(domainPerson.favorite)
            {
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
            }
            else if(!domainPerson.favorite){
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
            }

            binding.root.setOnClickListener {
                onClick(domainPerson.person_id)
            }

            binding.ibFavorite.setOnClickListener {
//                if(domainPerson.favorite)
//                {
//                    domainPerson.favorite=false
//                    onFavorite(domainPerson)
//                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
//                }
//                else if (!domainPerson.favorite)
//                {
//                    domainPerson.favorite=true
//                    onFavorite(domainPerson)
//                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
//                }
                onFavorite(domainPerson)
            }
        }


    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        return true
    }

    override fun onItemSwipe(position: Int) {
        onDelete(currentList[position])
    }


}