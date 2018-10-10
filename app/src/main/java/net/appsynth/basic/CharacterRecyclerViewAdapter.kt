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
                     characterDescTextView: TextView
    ) -> Unit)? = null

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
    )

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    inner class CharacterViewHolder(view: View?) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
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

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = characterList.size
}