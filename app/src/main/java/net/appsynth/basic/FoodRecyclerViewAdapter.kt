package net.appsynth.basic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_food.view.*

class FoodRecyclerViewAdapter : RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder>() {

    var itemClick: ((food: Food) -> Unit)? = null

    var foodList = mutableListOf(Food().apply {
        name = "Apple"
        thumb = R.drawable.apple
        desc
    }, Food().apply {
        name = "Butter popcorn"
        thumb = R.drawable.butter_popcorn
        desc
    }, Food().apply {
        name = "Cheese cake"
        thumb = R.drawable.cheese_cake
        desc
    }, Food().apply {
        name = "Chocolate cake"
        thumb = R.drawable.chocolate_cake
        desc
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemView.apply {
            foodNameTextView.text = foodList[position].name
            foodDescTextView.text = foodList[position].desc
            foodList[position].thumb?.let {
                foodThumbImageView.setImageResource(it)
            }

            setOnClickListener {
                itemClick?.invoke(foodList[position])
            }
        }
    }

    inner class FoodViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}