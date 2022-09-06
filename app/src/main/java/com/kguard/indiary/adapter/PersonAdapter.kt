package com.kguard.indiary.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.ItemRecyclerPersonBinding


class PersonAdapter(val onClick: (Int) -> Unit):RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){
    private var person:List<DomainPerson> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding= ItemRecyclerPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.setItem(person[position])
        holder.itemView.setOnClickListener{
            person[position].person_id?.let { it1 -> onClick(it1) }
        }
        //person[position].favorite=holder.setItem(person[position]).favorite
        //person[position] = holder.setItem(person[position])
    }

    override fun getItemCount(): Int {
        return person.size
    }
    inner class PersonViewHolder(private val binding: ItemRecyclerPersonBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setItem(person1: DomainPerson){

            binding.tvPeopleName.text= person1.name
            binding.tvMemoryDate.text=person1.make
            if(person1.favorite)
            {
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
            }
            else if(!person1.favorite){
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
            }

            binding.ibFavorite.setOnClickListener {
                if(person1.favorite)
                {
                    person1.favorite=false
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
                }
                else if (!person1.favorite)
                {
                    person1.favorite=true
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(person: List<DomainPerson>)
    {
        this.person=person
        notifyDataSetChanged()
    }



}