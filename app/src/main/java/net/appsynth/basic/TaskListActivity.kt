package net.appsynth.basic

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity(), TaskListFragment.OnListItemFragmentInteractionListener {

    private var isLargeScreen = false
    private var taskListFragment: TaskListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        isLargeScreen = containerTaskDetailFrameLayout != null
        taskListFragment = TaskListFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.containerTaskListFrameLayout, taskListFragment, "TAG_TASK_LIST")
                .commit()

        floatingActionButton.setOnClickListener {
            showAddTaskDialog()
        }
    }

    /**
     * OnListItemFragmentInteractionListener
     */
    override fun onListItemClicked(position: Int, taskName: String) {
        if (isLargeScreen) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerTaskDetailFrameLayout,
                            TaskDetailFragment.newInstance(taskName, position), "TAG_TASK_DETAIL")
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right)
                    .replace(R.id.containerTaskListFrameLayout,
                            TaskDetailFragment.newInstance(taskName, position), "TAG_TASK_DETAIL")
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun showAddTaskDialog() {

        val taskNameEditText = EditText(this)
        taskNameEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
                .setTitle("Add your task name.")
                .setView(taskNameEditText)
                .setCancelable(false)
                .setPositiveButton("ADD") { dialog, _ ->
                    val newTaskName = taskNameEditText.text.toString()
                    if (newTaskName.isNotEmpty()) {
                        taskListFragment?.addTaskList(newTaskName)
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }
}
