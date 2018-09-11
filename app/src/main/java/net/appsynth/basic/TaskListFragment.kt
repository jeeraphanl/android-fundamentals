package net.appsynth.basic

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_task_list.*

class TaskListFragment : Fragment() {

    interface OnListItemFragmentInteractionListener {
        fun onListItemClicked(position: Int, taskName: String)
    }

    private var listener: OnListItemFragmentInteractionListener? = null
    private lateinit var taskRecyclerViewAdapter: TaskRecyclerViewAdapter

    companion object {
        fun newInstance(): TaskListFragment {
            return TaskListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListItemFragmentInteractionListener) {
            listener = context
        } else {
            Toast.makeText(context, "onAttach error", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        /**
         * step 1
         * Set LinearLayoutManager RecyclerView
         * LinearLayoutManager isn’t the only layout provided by RecyclerView. Out of the box,
         * RecyclerView provides the GridLayoutManager and StaggeredGridLayoutManager.
         */
        taskRecyclerView.layoutManager = LinearLayoutManager(context)

        /**
         * step 2
         * Set adapter RecyclerView
         */
        taskRecyclerViewAdapter = TaskRecyclerViewAdapter()
        //set event click listener
        taskRecyclerViewAdapter.itemClick = { position: Int, taskName: String ->
            listener?.onListItemClicked(position, taskName)
        }

        taskRecyclerView.adapter = taskRecyclerViewAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    internal fun addTaskList(taskName: String) {
        taskRecyclerViewAdapter.taskList.add(taskName)
        taskRecyclerViewAdapter.notifyDataSetChanged()
    }
}
