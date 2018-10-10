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

    var characterList = mutableListOf(
            Character().apply {
                thumb = R.drawable.monkey
                name = "Monkey"
                desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
            }, Character().apply {
                thumb = R.drawable.onion
                name = "Onion"
                desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
            }
//            , Character().apply {
//                thumb = R.drawable.pineapple
//                name = "Pineapple"
//                desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
//            }, Character().apply {
//                thumb = R.drawable.sheep
//                name = "Sheep"
//                desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
//            }, Character().apply {
//                thumb = R.drawable.squid
//                name = "Squid"
//                desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
//            }, Character().apply {
//                thumb = R.drawable.lettuce
//                name = "Lettuce"
//                desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
//            }
    )

    //var characterList = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.itemView.apply {
            characterNameTextView.text = characterList[position].name
            characterDescTextView.text = characterList[position].desc
            characterList[position].thumb?.let {
                characterThumbImageView.setImageResource(it)
            }

            setOnClickListener {
                itemClick?.invoke(characterList[position], characterThumbImageView, characterNameTextView, characterDescTextView)
            }
        }
    }

    override fun getItemCount() = characterList.size

    inner class CharacterViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}