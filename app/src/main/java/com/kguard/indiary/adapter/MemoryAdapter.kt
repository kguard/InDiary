package com.kguard.indiary.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
            if(domainMemory.imageList[0] == null)
            {
                binding.ivMemory1.visibility= View.GONE
                binding.ivMemory2.visibility= View.GONE
                binding.ivMemory3.visibility= View.GONE
                binding.ivMemory4.visibility= View.GONE
                binding.ivMemory5.visibility= View.GONE
            }
            Glide.with(binding.ivMemory1)
                .load(domainMemory.imageList[0]?.toUri())
                .into(binding.ivMemory1)

            Glide.with(binding.ivMemory2)
                .load(domainMemory.imageList[1]?.toUri())
                .into(binding.ivMemory2)

            Glide.with(binding.ivMemory3)
                .load(domainMemory.imageList[2]?.toUri())
                .into(binding.ivMemory3)

            Glide.with(binding.ivMemory4)
                .load(domainMemory.imageList[3]?.toUri())
                .into(binding.ivMemory4)

            Glide.with(binding.ivMemory5)
                .load(domainMemory.imageList[4]?.toUri())
                .into(binding.ivMemory5)

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