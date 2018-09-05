package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_detail.*

class CardDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        //val position = intent?.getIntExtra("key_position", -1)
        //val cardName = intent?.getStringExtra("key_card_name")
        //cardNameTextView.text = "$position $cardName"

        //val bundle = intent.getBundleExtra("key_bundle")
        //val position = bundle.getInt("key_position")
        //val cardName = bundle?.getString("key_card_name")
        //cardNameTextView.text = "$position $cardName"

        val card: Card = intent.getParcelableExtra("key_parcelable")
        cardNameTextView.text = "${card.position} ${card.name}"
    }
}
