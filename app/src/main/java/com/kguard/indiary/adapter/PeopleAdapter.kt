package com.kguard.indiary.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kguard.indiary.R
import com.kguard.indiary.databinding.ItemRecyclerPeopleBinding
import com.kguard.indiary.db.PersonTag


class PeopleAdapter():RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private val personTag :List<PersonTag> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemRecyclerPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return personTag.size
    }
    inner class ViewHolder(private val binding: ItemRecyclerPeopleBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setItem(personTag: PersonTag){
            binding.tvPeopleName.text= personTag.person.name
            binding.rvPeopleTags.adapter= TagAdapter(personTag)
            binding.tvMemoryDate.text=personTag.person.make
            binding.ibFavorite.setOnClickListener {
                if(personTag.person.favorite==true)
                {
                    personTag.person.favorite=false
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
                }
                else if (personTag.person.favorite==false)
                {
                    personTag.person.favorite=true
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
                }
            }
        }
    }

}