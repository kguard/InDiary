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
            ): Boolean  = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: DomainPerson,
                newItem: DomainPerson
            ): Boolean = oldItem.person_id == newItem.person_id

        }
    }
   // private var person:List<DomainPerson> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding= ItemRecyclerPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setItem(getItem(position))
        holder.itemView.setOnClickListener{
            onClick(getItem(position).person_id)
        }
    }

    inner class PersonViewHolder(private val binding: ItemRecyclerPersonBinding) :RecyclerView.ViewHolder(binding.root), ItemHelperInterface {
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

            binding.ibFavorite.setOnClickListener {
                if(domainPerson.favorite)
                {
                    domainPerson.favorite=false
                    onFavorite(domainPerson)
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
                }
                else if (!domainPerson.favorite)
                {
                    domainPerson.favorite=true
                    onFavorite(domainPerson)
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
                }
            }
        }

        override fun onItemMove(from_position: Int, to_position: Int): Boolean {
            return true
        }

        override fun onItemSwipe(position: Int) {
            val list=currentList.toMutableList()
            onDelete(getItem(layoutPosition))
            list.removeAt(layoutPosition)
            submitList(list)
        }

    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        return true
    }

    override fun onItemSwipe(position: Int) {
        val list=currentList.toMutableList()
        //personOut(getItem(position))
        list.removeAt(position)
        onDelete(getItem(position))
        submitList(list)
    }


}