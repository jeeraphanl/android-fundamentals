package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_card_list.*

class CardListActivity : AppCompatActivity(), CardListFragment.OnListItemFragmentInteractionListener {

    private var isLargeScreen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        isLargeScreen = containerFragmentDetail != null

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, CardListFragment.newInstance(), "TAG_CARD_LIST")
                .commit()

        floatingActionButton.setOnClickListener {
            showAddCardDialog()
        }
    }

    /**
     * OnListItemFragmentInteractionListener
     */
    override fun onListItemClicked(position: Int, cardName: String) {
        if (isLargeScreen) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFragmentDetail,
                            CardDetailFragment.newInstance(cardName, position), "TAG_CARD_DETAIL")
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFragment,
                            CardDetailFragment.newInstance(cardName, position), "TAG_CARD_DETAIL")
                    .addToBackStack(null)
                    .commit()
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
                        //cardRecyclerViewAdapter.cardList.add(newCardName)
                        //cardRecyclerViewAdapter.notifyDataSetChanged()
                    }
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}
