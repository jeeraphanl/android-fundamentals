package net.appsynth.basic.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_character_detail.*
import net.appsynth.basic.model.Character
import net.appsynth.basic.R

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        //val position = intent?.getIntExtra("key_position", -1)
        //val characterName = intent?.getStringExtra("key_character_name")
        //characterNameTextView.text = "$position $characterName"

        //val bundle = intent.getBundleExtra("key_bundle")
        //val position = bundle.getInt("key_position")
        //val characterName = bundle?.getString("key_character_name")
        //characterNameTextView.text = "$position $characterName"

        val character: Character = intent.getParcelableExtra("key_parcelable")
        characterNameTextView.text = character.name
        characterDescTextView.text = character.desc
        character.thumb?.let {
            characterThumbImageView.setImageResource(it)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
