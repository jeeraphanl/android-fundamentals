package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_card_list.*
import kotlinx.android.synthetic.main.content_card_list.*

class CardListActivity : AppCompatActivity() {

    private lateinit var cardRecyclerViewAdapter: CardRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            showAddCardDialog()
        }

        /**
         * step 1
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        cardRecyclerView.layoutManager = LinearLayoutManager(this)

        // step 2
        cardRecyclerViewAdapter = CardRecyclerViewAdapter()
        cardRecyclerView.adapter = cardRecyclerViewAdapter
    }

    private fun showAddCardDialog() {

        val cardNameEditText = EditText(this)
        cardNameEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle("Add you card name.")
                .setView(cardNameEditText)
                .setPositiveButton("ADD") { dialog, which ->

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
