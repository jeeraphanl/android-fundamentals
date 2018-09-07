package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_card_list.*

class CardListActivity : AppCompatActivity(), CardListFragment.OnListItemFragmentInteractionListener {

    private var isLargeScreen = false
    private var cardListFragment: CardListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        isLargeScreen = containerCardDetailFrameLayout != null
        cardListFragment = CardListFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerCardListFrameLayout, cardListFragment, "TAG_CARD_LIST")
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
                    .replace(R.id.containerCardDetailFrameLayout,
                            CardDetailFragment.newInstance(cardName, position), "TAG_CARD_DETAIL")
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right)
                    .replace(R.id.containerCardListFrameLayout,
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
                        cardListFragment?.addCardList(newCardName)
                    }
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}
