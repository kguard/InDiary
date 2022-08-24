package com.kguard.indiary


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kguard.indiary.databinding.ItemRecyclerPeopleBinding
import com.kguard.indiary.db.InDiaryDB
import com.kguard.indiary.db.PersonTag


class PeopleAdapter(val inDiaryDB: InDiaryDB):RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    private val personTagDAO = inDiaryDB.personTagDao()
    private val itemTag: List<PersonTag> = personTagDAO.getPersonTag()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemRecyclerPeopleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    inner class ViewHolder(private val binding: ItemRecyclerPeopleBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: PersonTag){
            binding.tvPeopleName.text=item.person.name
            binding
        }
    }

}