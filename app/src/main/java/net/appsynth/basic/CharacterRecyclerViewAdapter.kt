package net.appsynth.basic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterRecyclerViewAdapter : RecyclerView.Adapter<CharacterRecyclerViewAdapter.CharacterViewHolder>() {

    var itemClick: ((character: Character,
                     characterThumbImageView: ImageView,
                     characterNameTextView: TextView,
                     characterDescTextView: TextView) -> Unit)? = null

    var characterList = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.apply {
            characterThumbImageView
            characterNameTextView.text = characterList[position].name
            characterDescTextView.text = characterList[position].desc

            setOnClickListener {
                itemClick?.invoke(characterList[position], characterThumbImageView, characterNameTextView, characterDescTextView)
            }
        }
    }

    inner class CharacterViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}