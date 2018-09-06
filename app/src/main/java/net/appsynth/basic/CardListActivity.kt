package net.appsynth.basic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CardListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, CardListFragment.newInstance(), "TAG")
                .commit()
    }
}
