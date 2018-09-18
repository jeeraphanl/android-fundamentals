package net.appsynth.basic

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_receive.*

class ReceiveIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)

        if (intent?.action == Intent.ACTION_SEND) {
            val subject = intent?.getStringExtra(Intent.EXTRA_SUBJECT)
            val message = intent?.getStringExtra(Intent.EXTRA_TEXT)

            subjectTextView.text = subject
            messageTextView.text = message
        }
    }
}