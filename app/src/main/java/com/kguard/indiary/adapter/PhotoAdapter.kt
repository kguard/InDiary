package com.kguard.indiary.adapter

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kguard.indiary.databinding.ItemRecyclerPhotoBinding

class PhotoAdapter(val onClick : (Int) -> Unit): ListAdapter<String, PhotoAdapter.PhotoViewHolder>(PhotoAdapter.diffUtil) {

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem.hashCode() == newItem.hashCode()
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        }
    }

   // private var photos:ArrayList<String?> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding= ItemRecyclerPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.setImage(it) }
//        holder.itemView.setOnClickListener {
//            onClick(position)
//            //photos.removeAt(position)
//
//            Log.d("======================", "onBindViewHolder: ${position}")
//        }
    }

    inner class PhotoViewHolder(val binding: ItemRecyclerPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun setImage(photo:String){
            Glide.with(binding.ivPhoto)
                .load(photo.toUri())
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(binding.ivPhoto)

            binding.root.setOnClickListener {
                onClick(layoutPosition)
            }
        }
    }
    fun updatePhoto(photos:List<String>)
    {
        val list=currentList.toMutableList()
        list.clear()
        list.addAll(photos)
        submitList(list)
    }

}