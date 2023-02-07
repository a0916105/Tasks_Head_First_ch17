package tw.idv.jew.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""    //用來儲存工作名稱

    fun addTask(){
        viewModelScope.launch {//在view model的作用域啟動協同程序
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)    //加入資料庫
        }
    }
}