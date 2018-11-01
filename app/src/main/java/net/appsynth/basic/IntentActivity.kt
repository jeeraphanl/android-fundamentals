package net.appsynth.basic

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        takPhotoButton.setOnClickListener {
            openCamera()
        }

        messageButton.setOnClickListener {
            sendText()
        }
    }

    private fun sendText() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Leave early")
        intent.putExtra(Intent.EXTRA_TEXT, "Hi everyone, Today I will leave at 5 AM.")
        startActivity(intent)

    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val imageBitmap = extras?.get("data") as Bitmap
            photoImageView.setImageBitmap(imageBitmap)
        }
    }
}
