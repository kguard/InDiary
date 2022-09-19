package com.kguard.indiary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kguard.indiary.data.CustomCharacter
import com.kguard.indiary.databinding.ItemCustomCharacterBinding

/**
 *  todo character
 */
class AddCharacterAdapter():RecyclerView.Adapter<AddCharacterAdapter.AddCharacterViewHolder>() {
    private var character = arrayListOf<CustomCharacter>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCharacterViewHolder {
        val binding=ItemCustomCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddCharacterViewHolder, position: Int) {
        holder.setItem(character[position])

    }

    override fun getItemCount(): Int {
        return character.size
    }
    inner class AddCharacterViewHolder(val binding: ItemCustomCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setItem(customCharacter: CustomCharacter){
            binding.tvAddCharacterName.text=customCharacter.title
            binding.etAddPersonName.editText?.setText(customCharacter.contents)
            binding.etAddPersonName.editText?.hint = customCharacter.title
        }

    }
    fun getList():List<CustomCharacter>
    {
        return this.character
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addData(character: CustomCharacter){
        this.character.add(character)
        notifyDataSetChanged()
    }

}