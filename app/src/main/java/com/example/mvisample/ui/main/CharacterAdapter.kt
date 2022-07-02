package com.example.mvisample.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvisample.R
import com.example.mvisample.databinding.ItemCharacterBinding
import com.example.mvisample.ktx.loadImage
import com.example.mvisample.model.result.character.Character

class CharacterAdapter(val onItemClick: (character: Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private val characters = arrayListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(items: List<Character>) {
        characters.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character) {

            val binding = ItemCharacterBinding.bind(itemView)

            binding.apply {

                root.setOnClickListener {
                    onItemClick(character)
                }

                textView.text = character.name

                imageView.loadImage(character.image)

            }

        }

    }

}