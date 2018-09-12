package net.appsynth.basic

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.util.Pair
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_character_list.*
import kotlinx.android.synthetic.main.content_character_list.*

class CharacterListActivity : AppCompatActivity() {

    private lateinit var characterRecyclerViewAdapter: CharacterRecyclerViewAdapter

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        setSupportActionBar(toolbar)

        floatingActionButton.setOnClickListener {
            showAddCharacterDialog()
        }

        /**
         * step 1
         * Set LinearLayoutManager RecyclerView
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        characterRecyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * step 2
         * Set adapter RecyclerView
         */
        characterRecyclerViewAdapter = CharacterRecyclerViewAdapter()

        //set event click listener
        characterRecyclerViewAdapter.itemClick = {character: Character,
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

        characterRecyclerView.adapter = characterRecyclerViewAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CharacterDetailActivity.REQUEST_CODE) {
            when(resultCode) {
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
                        }

                        characterRecyclerViewAdapter.characterList.add(character)
                        characterRecyclerViewAdapter.notifyDataSetChanged()
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
