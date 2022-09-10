package com.kguard.indiary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.databinding.ItemRecyclerMemoryBinding
import com.kguard.indiary.util.ItemHelperInterface

class MemoryAdapter(
    val onClick: (Int) -> Unit,
    val onDelete:(DomainMemory) ->Unit
): ListAdapter<DomainMemory, MemoryAdapter.MemoryViewHolder>(MemoryAdapter.diffUtil),
    ItemHelperInterface {
    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<DomainMemory>() {
            override fun areItemsTheSame(
                oldItem: DomainMemory,
                newItem: DomainMemory
            ): Boolean  = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: DomainMemory,
                newItem: DomainMemory
            ): Boolean = oldItem.memory_id == newItem.memory_id

        }
    }
   // private var memory:List<DomainMemory> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val binding= ItemRecyclerMemoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.setItem(getItem(position))
        holder.itemView.setOnClickListener {
            onClick(getItem(position).memory_id)
        }
    }


    inner class MemoryViewHolder(private val binding: ItemRecyclerMemoryBinding):RecyclerView.ViewHolder(binding.root) {
        fun setItem(domainMemory: DomainMemory){
            binding.tvMemoryTitle.text=domainMemory.title
            binding.tvMemoryDate.text=domainMemory.date
//            if (domainMemory.image1 != null) { binding.ivMemory1.setImageResource()}
//            if (domainMemory.image2 != null) { binding.ivMemory2.setImageResource()}
//            if (domainMemory.image3 != null) { binding.ivMemory3.setImageResource()}
//            if (domainMemory.image4 != null) { binding.ivMemory4.setImageResource()}
//            if (domainMemory.image5 != null) { binding.ivMemory5.setImageResource()}
        }
    }
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(memory: List<DomainMemory>)
//    {
//        this.memory=memory
//        notifyDataSetChanged()
//    }
override fun onItemMove(from_position: Int, to_position: Int): Boolean {
    return true
}

    override fun onItemSwipe(position: Int) {
        val list=currentList.toMutableList()
        onDelete(getItem(position))
        list.removeAt(position)
        submitList(list)
    }

}