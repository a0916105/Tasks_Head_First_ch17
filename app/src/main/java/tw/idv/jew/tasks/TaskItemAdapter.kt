package tw.idv.jew.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) { //為了處理串列並搭配DiffUtil使用，改繼承ListAdapter
    //將layout充氣並回傳view holder，當APP需要建立view holder時會呼叫它。parent是指recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)
    //APP需要在view holder裡面顯示資料時會呼叫它
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)   //從adapter的backing list取得項目
        holder.bind(item)
    }

    class TaskItemViewHolder(val rootView: CardView)
        : RecyclerView.ViewHolder(rootView) {
        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        companion object {
            //建立每一個view holder並充氣它的layout
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }
        //將資料加入view holder的layout
        fun bind(item: Task) {
            //將工作名稱加入layout的根view
            taskName.text = item.taskName
            taskDone.isChecked = item.taskDone
        }
    }
}