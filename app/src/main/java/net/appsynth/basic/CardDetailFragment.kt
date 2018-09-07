package net.appsynth.basic


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_card_detail.*

class CardDetailFragment : Fragment() {

    companion object {

        const val KEY_CARD_NAME = "kCardName"
        const val KEY_POSITION = "kPosition"

        fun newInstance(cardName: String, position: Int): CardDetailFragment {
            return CardDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CARD_NAME, cardName)
                    putInt(KEY_POSITION, position)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position: Int = arguments?.getInt(KEY_POSITION) ?: 0
        val cardName = arguments?.getString(KEY_CARD_NAME)
        cardNameTextView.text = "$position $cardName"
    }
}
