package com.kguard.indiary.adapter


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.kguard.data.local.entity.Person
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.R
import com.kguard.indiary.databinding.ItemRecyclerPersonBinding
import com.kguard.indiary.viewmodel.PersonViewModel


class PersonAdapter(val onClick: (Int) -> Unit):RecyclerView.Adapter<PersonAdapter.ViewHolder>(){
    private var person:List<DomainPerson> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemRecyclerPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(person[position])
        holder.itemView.setOnClickListener{
            onClick(person[position].person_id)
        }
        //person[position].favorite=holder.setItem(person[position]).favorite
        //person[position] = holder.setItem(person[position])
    }

    override fun getItemCount(): Int {
        return person.size
    }
    inner class ViewHolder(private val binding: ItemRecyclerPersonBinding) :RecyclerView.ViewHolder(binding.root) {
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

    fun setData(person: List<DomainPerson>)
    {
        this.person=person
        notifyDataSetChanged()
    }



}