package tw.idv.jew.tasks

import androidx.recyclerview.widget.DiffUtil

class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {
    //檢查兩個物件是否參考同一個項目
    override fun areItemsTheSame(oldItem: Task, newItem: Task)
            = (oldItem.taskId == newItem.taskId)

    //檢查兩個物件有沒有相同的內容
    override fun areContentsTheSame(oldItem: Task, newItem: Task)
            = (oldItem == newItem)
}