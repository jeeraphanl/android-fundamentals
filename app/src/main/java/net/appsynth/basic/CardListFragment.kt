package net.appsynth.basic

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_card_list.*

class CardListFragment : Fragment() {

    interface OnListItemFragmentInteractionListener {
        fun onListItemClicked(position: Int, cardName: String)
    }

    private var listener: OnListItemFragmentInteractionListener? = null
    private lateinit var cardRecyclerViewAdapter: CardRecyclerViewAdapter

    companion object {
        fun newInstance(): CardListFragment {
            return CardListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListItemFragmentInteractionListener) {
            listener = context
        } else {
            Toast.makeText(context, "onAttach error", Toast.LENGTH_LONG).show()
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
            listener?.onListItemClicked(position, cardName)
        }

        cardRecyclerView.adapter = cardRecyclerViewAdapter
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
        listener = null
    }

    internal fun addCardList(cardName: String) {
        cardRecyclerViewAdapter.cardList.add(cardName)
        cardRecyclerViewAdapter.notifyDataSetChanged()
    }
}
