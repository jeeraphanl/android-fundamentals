package net.appsynth.basic

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_card_list.*

class CardListFragment : Fragment() {

    private lateinit var cardRecyclerViewAdapter: CardRecyclerViewAdapter

    companion object {
        fun newInstance(): CardListFragment {
            return CardListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        /**
         * step 1
         * Set LinearLayoutManager RecyclerView
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        cardRecyclerView.layoutManager = LinearLayoutManager(context)

        /**
         * step 2
         * Set adapter RecyclerView
         */
        cardRecyclerViewAdapter = CardRecyclerViewAdapter()
        //set event click listener
        cardRecyclerViewAdapter.itemClick = { position: Int, cardName: String ->

        }
        cardRecyclerView.adapter = cardRecyclerViewAdapter

        floatingActionButton.setOnClickListener {
            showAddCardDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun showAddCardDialog() {

        context?.let { context ->
            val cardNameEditText = EditText(context)
            cardNameEditText.inputType = InputType.TYPE_CLASS_TEXT

            AlertDialog.Builder(context)
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
}
