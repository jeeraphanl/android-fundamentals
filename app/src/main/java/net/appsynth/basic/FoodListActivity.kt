package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_food_list.*

class FoodListActivity : AppCompatActivity(), FoodListFragment.OnListItemFragmentInteractionListener {

    private var isLargeScreen = false
    private var foodListFragment: FoodListFragment? = null

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
    override fun onListItemClicked(position: Int, foodName: String) {
        if (isLargeScreen) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFoodDetailFrameLayout,
                            FoodDetailFragment.newInstance(foodName, position), "TAG_FOOD_DETAIL")
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right)
                    .replace(R.id.containerFoodListFrameLayout,
                            FoodDetailFragment.newInstance(foodName, position), "TAG_FOOD_DETAIL")
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
                        foodListFragment?.addFoodList(newFoodName)
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
