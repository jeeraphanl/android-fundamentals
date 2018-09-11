package net.appsynth.basic

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.content_task_list.*

class TaskListActivity : AppCompatActivity() {

    private lateinit var taskRecyclerViewAdapter: TaskRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(toolbar)

        floatingActionButton.setOnClickListener {
            showAddTaskDialog()
        }

        /**
         * step 1
         * Set LinearLayoutManager RecyclerView
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        taskRecyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * step 2
         * Set adapter RecyclerView
         */
        taskRecyclerViewAdapter = TaskRecyclerViewAdapter()

        //set event click listener
        taskRecyclerViewAdapter.itemClick = { position: Int, taskName: String ->
            val intent = Intent(this, TaskDetailActivity::class.java)


            //intent.putExtra("key_position", position)
            //intent.putExtra("key_task_name", taskName)

            //intent.putExtra("key_bundle", Bundle().apply {
            //    putInt("key_position", position)
            //    putString("key_task_name", taskName)
            //})

            intent.putExtra("key_parcelable", Task().apply {
                title = taskName
                this.position = position
            })

            //startActivity(intent)
            startActivityForResult(intent, TaskDetailActivity.REQUEST_CODE)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        taskRecyclerView.adapter = taskRecyclerViewAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TaskDetailActivity.REQUEST_CODE) {
            when(resultCode) {
                Activity.RESULT_CANCELED -> Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
                Activity.RESULT_OK -> Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showAddTaskDialog() {

        val taskNameEditText = EditText(this)
        taskNameEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle("Add your task title.")
                .setView(taskNameEditText)
                .setPositiveButton("ADD") { dialog, _ ->
                    val newTaskName = taskNameEditText.text.toString()
                    if (newTaskName.isNotEmpty()) {
                        taskRecyclerViewAdapter.taskList.add(newTaskName)
                        taskRecyclerViewAdapter.notifyDataSetChanged()
                    }
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}
