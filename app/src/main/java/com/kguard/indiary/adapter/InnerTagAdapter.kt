package com.kguard.indiary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kguard.indiary.databinding.ItemTagCardviewBinding
import com.kguard.indiary.db.Tag

class InnerTageAdapter(var tag: List<Tag>):RecyclerView.Adapter<InnerTageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemTagCardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(tag[position])
    }

    override fun getItemCount(): Int {
        return tag.size
    }
    inner class ViewHolder(private val binding: ItemTagCardviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(tag: Tag){
            binding.tvTagCard.text=tag.name
        }
    }
    fun setData(tag: List<Tag>)
    {
        this.tag=tag
        notifyDataSetChanged()
    }

}