package tw.idv.jew.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""    //用來儲存工作名稱

    val tasks = dao.getAll()    //從資料庫取得記錄

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask


    fun addTask(){
        viewModelScope.launch {//在view model的作用域啟動協同程序
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)    //加入資料庫
        }
    }

    //被按後要執行的工作，通常用來傳值
    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    //通常用在工作完成後，自動將值設回預設
    fun onTaskNavigated() {
        _navigateToTask.value = null
    }

}