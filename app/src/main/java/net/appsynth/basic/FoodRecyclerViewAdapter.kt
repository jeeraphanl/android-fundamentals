package net.appsynth.basic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_food.view.*

class FoodRecyclerViewAdapter : RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder>() {

    var itemClick: ((index: Int, foodName: String) -> Unit)? = null

    var foodList = mutableListOf("Knight", "Golem", "Baby Dragon", "Wizard")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemView.apply {
            foodNameTextView.text = foodList[position]

            setOnClickListener {
                itemClick?.invoke(position, foodList[position])
            }
        }
    }

    inner class FoodViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}