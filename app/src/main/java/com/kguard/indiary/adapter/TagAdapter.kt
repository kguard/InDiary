package com.kguard.indiary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kguard.indiary.databinding.ItemTagCardviewBinding
import com.kguard.indiary.db.Tag

class TagAdapter(val tag: List<Tag>?):RecyclerView.Adapter<TagAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemTagCardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tag?.get(position)?.let { holder.setItem(it) }
    }

    override fun getItemCount(): Int {
        if(tag == null)
            return 0
        return tag.size
    }
    inner class ViewHolder(private val binding: ItemTagCardviewBinding):RecyclerView.ViewHolder(binding.root) {
        fun setItem(tag: Tag){
            binding.tvTagCard.text=tag.name
        }
    }

}