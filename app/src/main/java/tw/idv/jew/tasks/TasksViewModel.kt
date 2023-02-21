package tw.idv.jew.tasks

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""    //用來儲存工作名稱

    private val tasks = dao.getAll()    //從資料庫取得記錄
    val tasksString = Transformations.map(tasks) {
            tasks -> formatTasks(tasks) //將tasks(LiveData<List<Task>>)轉換成LiveData<String>
    }

    fun addTask(){
        viewModelScope.launch {//在view model的作用域啟動協同程序
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)    //加入資料庫
        }
    }

    fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") {
                str, item -> str + '\n' + formatTask(item)
        }
    }

    fun formatTask(task: Task): String {
        var str = "ID: ${task.taskId}"
        str += '\n' + "Name: ${task.taskName}"
        str += '\n' + "Complete: ${task.taskDone}" + '\n'
        return str
    }
}