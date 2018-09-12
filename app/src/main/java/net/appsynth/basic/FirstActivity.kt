package net.appsynth.basic

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import android.view.View
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        icLauncherImageView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair<View, String>(icLauncherImageView, "icLauncherTransition"))
            startActivity(intent, options.toBundle())
        }
    }
}
