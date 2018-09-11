package net.appsynth.basic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_task.view.*

class TaskRecyclerViewAdapter : RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder>() {

    var itemClick: ((index: Int, taskName: String) -> Unit)? = null

    var taskList = mutableListOf("Knight", "Golem", "Baby Dragon", "Wizard")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.itemView.apply {
            taskNameTextView.text = taskList[position]

            setOnClickListener {
                itemClick?.invoke(position, taskList[position])
            }
        }
    }

    inner class TaskViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}