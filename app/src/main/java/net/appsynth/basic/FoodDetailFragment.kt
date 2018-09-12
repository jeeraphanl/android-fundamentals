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
        const val KEY_POSITION = "kPosition"

        fun newInstance(foodName: String, position: Int): FoodDetailFragment {
            return FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_FOOD_NAME, foodName)
                    putInt(KEY_POSITION, position)
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

        val position: Int = arguments?.getInt(KEY_POSITION) ?: 0
        val foodName = arguments?.getString(KEY_FOOD_NAME)
        foodNameTextView.text = "$position $foodName"
    }
}
