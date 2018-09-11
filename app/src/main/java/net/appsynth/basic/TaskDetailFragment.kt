package net.appsynth.basic


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_task_detail.*

class TaskDetailFragment : Fragment() {

    companion object {

        const val KEY_TASK_NAME = "kTaskName"
        const val KEY_POSITION = "kPosition"

        fun newInstance(taskName: String, position: Int): TaskDetailFragment {
            return TaskDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TASK_NAME, taskName)
                    putInt(KEY_POSITION, position)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position: Int = arguments?.getInt(KEY_POSITION) ?: 0
        val taskName = arguments?.getString(KEY_TASK_NAME)
        taskNameTextView.text = "$position $taskName"
    }
}
