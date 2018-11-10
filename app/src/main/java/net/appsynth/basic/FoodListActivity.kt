package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_food_list.*
import java.util.*

class FoodListActivity : AppCompatActivity(), FoodListFragment.OnListItemFragmentInteractionListener {

    private var isLargeScreen = false
    private var foodListFragment: FoodListFragment? = null

    private val foodImageList = arrayListOf(
            R.drawable.apple,
            R.drawable.butter_popcorn,
            R.drawable.cheese_cake,
            R.drawable.chocolate_cake,
            R.drawable.chocolate_frappe,
            R.drawable.hotdog,
            R.drawable.kiwi,
            R.drawable.magnum,
            R.drawable.nugget,
            R.drawable.orange,
            R.drawable.porkchop,
            R.drawable.potato_chips,
            R.drawable.roasted_chicken,
            R.drawable.sugar_doughnut,
            R.drawable.sugar_free_gum,
            R.drawable.watermelon)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        isLargeScreen = containerFoodDetailFrameLayout != null
        foodListFragment = FoodListFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerFoodListFrameLayout, foodListFragment, "TAG_FOOD_LIST")
                .commit()

        floatingActionButton.setOnClickListener {
            showAddFoodDialog()
        }
    }

    /**
     * OnListItemFragmentInteractionListener
     */
    override fun onListItemClicked(food: Food) {
        if (isLargeScreen) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFoodDetailFrameLayout,
                            FoodDetailFragment.newInstance(food), "TAG_FOOD_DETAIL")
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right)
                    .replace(R.id.containerFoodListFrameLayout,
                            FoodDetailFragment.newInstance(food), "TAG_FOOD_DETAIL")
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun showAddFoodDialog() {

        val foodNameEditText = EditText(this)
        foodNameEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle("Add your food name.")
                .setView(foodNameEditText)
                .setCancelable(false)
                .setPositiveButton("ADD") { dialog, _ ->
                    val newFoodName = foodNameEditText.text.toString()
                    if (newFoodName.isNotEmpty()) {
                        val random = Random()
                        val index = random.nextInt(foodImageList.size - 1)
                        val food = Food().apply {
                            name = newFoodName
                            desc = "To use these attributes, add the tools namespace to the root element of each XML file where you would like to use them, as shown here."
                            thumb = foodImageList[index]
                        }

                        foodListFragment?.addFoodList(food)
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}
