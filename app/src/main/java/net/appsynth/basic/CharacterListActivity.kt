package net.appsynth.basic

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.InputType
import android.util.Pair
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_character_list.*
import java.util.*

class CharacterListActivity : AppCompatActivity() {

    private val characterImageList = arrayListOf(
            R.drawable.belly,
            R.drawable.cabbage,
            R.drawable.cactus,
            R.drawable.devil,
            R.drawable.green_onion,
            R.drawable.monkey,
            R.drawable.pineapple,
            R.drawable.puffer,
            R.drawable.sheep,
            R.drawable.squid)

    private lateinit var viewAdapter: CharacterRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        addButton.setOnClickListener {
            showAddCharacterDialog()
        }

        /**
         * step 1
         * Set LinearLayoutManager RecyclerView
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        viewManager = LinearLayoutManager(this)
        //LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //Reverse

        //viewManager = GridLayoutManager(this, 3)
        //GridLayoutManager(this, 3,GridLayoutManager.HORIZONTAL, false) ////Reverse

        //viewManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        /**
         * step 2
         * Set adapter RecyclerView
         */
        viewAdapter = CharacterRecyclerViewAdapter()

        characterRecyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        //set event click listener
        viewAdapter.itemClick = { character: Character,
                                                   characterThumbImageView: ImageView,
                                                   characterNameTextView: TextView,
                                                   characterDescTextView: TextView ->

            val options = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair<View, String>(characterThumbImageView, "characterThumbTransition"),
                    Pair<View, String>(characterNameTextView, "characterNameTransition"),
                    Pair<View, String>(characterDescTextView, "characterDescTransition"))
            val intent = Intent(this, CharacterDetailActivity::class.java)


            //intent.putExtra("key_position", position)
            //intent.putExtra("key_character_name", characterName)

            //intent.putExtra("key_bundle", Bundle().apply {
            //    putInt("key_position", position)
            //    putString("key_character_name", characterName)
            //})

            intent.putExtra("key_parcelable", Character().apply {
                this.name = character.name
                this.thumb = character.thumb
                this.desc = character.desc
            })

            //startActivity(intent, options.toBundle())
            startActivityForResult(intent, CharacterDetailActivity.REQUEST_CODE, options.toBundle())
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        characterRecyclerView.adapter = viewAdapter

        viewAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CharacterDetailActivity.REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_CANCELED -> Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
                Activity.RESULT_OK -> Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showAddCharacterDialog() {

        val characterNameEditText = EditText(this)
        characterNameEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle("Add your character name.")
                .setView(characterNameEditText)
                .setCancelable(false)
                .setPositiveButton("ADD") { dialog, _ ->
                    val newCharacterName = characterNameEditText.text.toString()
                    if (newCharacterName.isNotEmpty()) {

                        val character = Character().apply {
                            name = newCharacterName
                            desc = getString(R.string.desc_character)
                            val random = Random()
                            val index = random.nextInt(characterImageList.size - 1)
                            thumb = characterImageList[index]
                        }

                        viewAdapter.characterList.add(character)
                        viewAdapter.notifyDataSetChanged()
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
