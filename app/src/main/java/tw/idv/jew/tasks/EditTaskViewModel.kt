package tw.idv.jew.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, val dao: TaskDao) : ViewModel() {
    val task = dao.get(taskId)  //將task屬性設為LiveData<Task>

    //TaskDao的update()與delete()方法是協同程序，所以必須在launch區塊呼叫它們
    //!!：如果是null會丟出null pointer異常
    fun updateTask() {
        viewModelScope.launch {
            dao.update(task.value!!)
        }
    }
    fun deleteTask() {
        viewModelScope.launch {
            dao.delete(task.value!!)
        }
    }
}