package tw.idv.jew.tasks

import androidx.lifecycle.ViewModel

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""    //用來儲存工作名稱

    fun addTask(){
        val task = Task()
        task.taskName = newTaskName
        dao.insert(task)    //加入資料庫
    }
}