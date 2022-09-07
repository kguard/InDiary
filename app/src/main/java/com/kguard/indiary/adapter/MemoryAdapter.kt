package com.kguard.indiary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.databinding.ItemRecyclerMemoryBinding

class MemoryAdapter(val onClick: (Int) -> Unit): RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder>() {
    private var memory:List<DomainMemory> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val binding= ItemRecyclerMemoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.setItem(memory[position])
        holder.itemView.setOnClickListener {
            onClick(memory[position].memory_id)
        }
    }

    override fun getItemCount(): Int {
        return memory.size
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
    @SuppressLint("NotifyDataSetChanged")
    fun setData(memory: List<DomainMemory>)
    {
        this.memory=memory
        notifyDataSetChanged()
    }

}