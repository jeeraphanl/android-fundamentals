package net.appsynth.basic


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_food_detail.*

class FoodDetailFragment : Fragment() {

    companion object {

        const val KEY_FOOD_NAME = "kFoodName"
        const val KEY_FOOD_DESC = "kDesc"
        const val KEY_FOOD_THUMB = "kThumb"

        fun newInstance(food: Food): FoodDetailFragment {
            return FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FOOD_NAME, food.name)
                    putString(KEY_FOOD_DESC, food.desc)
                    food.thumb?.let {
                        putInt(KEY_FOOD_THUMB, it)
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodName = arguments?.getString(KEY_FOOD_NAME)
        val foodDesc = arguments?.getString(KEY_FOOD_DESC)
        val foodThumb = arguments?.getInt(KEY_FOOD_THUMB)

        foodNameTextView.text = foodName
        foodDescTextView.text = foodDesc
        foodThumb?.let {
            foodThumbImageView.setImageResource(it)
        }
    }
}
