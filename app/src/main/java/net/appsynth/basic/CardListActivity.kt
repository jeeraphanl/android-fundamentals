package net.appsynth.basic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.content_card_list.*

class CardListActivity : AppCompatActivity() {

    private lateinit var cardRecyclerViewAdapter: CardRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        setSupportActionBar(toolbar)

        floatingActionButton.setOnClickListener {
            showAddCardDialog()
        }

        /**
         * step 1
         * Set LinearLayoutManager RecyclerView
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        cardRecyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * step 2
         * Set adapter RecyclerView
         */
        cardRecyclerViewAdapter = CardRecyclerViewAdapter()

        //set event click listener
        cardRecyclerViewAdapter.itemClick = { position: Int, cardName: String ->
            val intent = Intent(this, CardDetailActivity::class.java)


            //intent.putExtra("key_position", position)
            //intent.putExtra("key_card_name", cardName)

            //intent.putExtra("key_bundle", Bundle().apply {
            //    putInt("key_position", position)
            //    putString("key_card_name", cardName)
            //})

            intent.putExtra("key_parcelable", Card().apply {
                name = cardName
                this.position = position
            })

            //startActivity(intent)
            startActivityForResult(intent, CardDetailActivity.REQUEST_CODE)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        cardRecyclerView.adapter = cardRecyclerViewAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CardDetailActivity.REQUEST_CODE) {
            when(resultCode) {
                Activity.RESULT_CANCELED -> Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
                Activity.RESULT_OK -> Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showAddCardDialog() {

        val cardNameEditText = EditText(this)
        cardNameEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle("Add you card name.")
                .setView(cardNameEditText)
                .setPositiveButton("ADD") { dialog, _ ->
                    val newCardName = cardNameEditText.text.toString()
                    if (newCardName.isNotEmpty()) {
                        cardRecyclerViewAdapter.cardList.add(newCardName)
                        cardRecyclerViewAdapter.notifyDataSetChanged()
                    }
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}
