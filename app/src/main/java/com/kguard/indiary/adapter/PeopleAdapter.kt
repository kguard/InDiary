package com.kguard.indiary.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.kguard.indiary.R
import com.kguard.indiary.databinding.ItemRecyclerPeopleBinding
import com.kguard.indiary.db.Person


class PeopleAdapter():RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private var person:List<Person> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemRecyclerPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(person[position])
    }

    override fun getItemCount(): Int {

        return person.size
    }
    inner class ViewHolder(private val binding: ItemRecyclerPeopleBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setItem(person: Person){
            binding.tvPeopleName.text= person.name
            binding.rvPeopleTags.adapter= person.Tag?.let { InnerTageAdapter(it) }
            binding.tvMemoryDate.text=person.make
            binding.ibFavorite.setOnClickListener {
                if(person.favorite==true)
                {
                    person.favorite=false
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_off)
                }
                else if (person.favorite==false)
                {
                    person.favorite=true
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite_on)
                }
            }
        }
    }
    fun setData(person: List<Person>)
    {
        this.person=person
        notifyDataSetChanged()
    }

}