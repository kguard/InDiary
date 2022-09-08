package com.kguard.indiary.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.ItemRecyclerPersonBinding
import com.kguard.indiary.util.PersonItemHelperInterface


class PersonAdapter(
    val onClick: (Int) -> Unit,
    val onDelete: (DomainPerson) -> Unit
):ListAdapter<DomainPerson, PersonAdapter.PersonViewHolder>(diffUtil),PersonItemHelperInterface{
//    val personOut:(DomainPerson)->Unit,

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
        //person[position].favorite=holder.setItem(person[position]).favorite
        //person[position] = holder.setItem(person[position])
    }
//
//    override fun getItemCount(): Int {
//        return person.size
//    }
    inner class PersonViewHolder(private val binding: ItemRecyclerPersonBinding) :RecyclerView.ViewHolder(binding.root) {
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
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
                }
                else if (!domainPerson.favorite)
                {
                    domainPerson.favorite=true
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
                }
            }
        }

    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(person: List<DomainPerson>)
//    {
//        this.person=person
//        notifyDataSetChanged()
//    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        return true
    }

    override fun onItemSwipe(position: Int) {
        val list=currentList.toMutableList()
        onDelete(getItem(position))
        //personOut(getItem(position))
        list.removeAt(position)
        submitList(list)
    }


}