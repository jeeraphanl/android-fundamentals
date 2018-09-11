package net.appsynth.basic

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //val position = intent?.getIntExtra("key_position", -1)
        //val taskName = intent?.getStringExtra("key_task_name")
        //taskNameTextView.text = "$position $taskName"

        //val bundle = intent.getBundleExtra("key_bundle")
        //val position = bundle.getInt("key_position")
        //val taskName = bundle?.getString("key_task_name")
        //taskNameTextView.text = "$position $taskName"

        val task: Task = intent.getParcelableExtra("key_parcelable")
        taskNameTextView.text = "${task.position} ${task.name}"

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
