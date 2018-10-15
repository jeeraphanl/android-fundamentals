package net.appsynth.basic

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameButton.setOnClickListener {
            val intent = Intent(this, FrameLayoutActivity::class.java)
            startActivity(intent)
        }

        linearVerticalButton.setOnClickListener {
            val intent = Intent(this, LinearVerticalLayoutActivity::class.java)
            startActivity(intent)
        }

        linearHorizontalButton.setOnClickListener {
            val intent = Intent(this, LinearHorizontalLayoutActivity::class.java)
            startActivity(intent)
        }

        relativeButton.setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        constraintButton.setOnClickListener {
            val intent = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}