package tw.idv.jew.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.idv.jew.tasks.databinding.TaskItemBinding

class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) { //為了處理串列並搭配DiffUtil使用，改繼承ListAdapter
    //將layout充氣並回傳view holder，當APP需要建立view holder時會呼叫它。parent是指recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)
    //APP需要在view holder裡面顯示資料時會呼叫它
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)   //從adapter的backing list取得項目
        holder.bind(item)
    }

    class TaskItemViewHolder(val binding: TaskItemBinding)  //為了使用binding類別來充氣layout，所以改成接收binding物件
        : RecyclerView.ViewHolder(binding.root) {   //binding.root是layout的根view

        companion object {
            //建立每一個view holder並充氣它的layout
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //改用binding方式來將layout充氣
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)  //使用binding變數來建立ViewHolder
            }
        }
        //將資料加入view holder的layout
        fun bind(item: Task) {
            //設定layout的data binding變數
            binding.task = item
        }
    }
}