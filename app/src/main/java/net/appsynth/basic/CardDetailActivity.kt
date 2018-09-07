package net.appsynth.basic

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card_detail.*

class CardDetailActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1001
    }

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

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        okButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
