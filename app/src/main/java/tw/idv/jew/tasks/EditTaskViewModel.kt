package tw.idv.jew.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, val dao: TaskDao) : ViewModel() {
    val task = dao.get(taskId)  //將task屬性設為LiveData<Task>

    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    //TaskDao的update()與delete()方法是協同程序，所以必須在launch區塊呼叫它們
    //!!：如果是null會丟出null pointer異常
    //_navigateToList.value為true時，EditTaskFragment必須回TasksFragment
    fun updateTask() {
        viewModelScope.launch {
            dao.update(task.value!!)
            _navigateToList.value = true
        }
    }
    fun deleteTask() {
        viewModelScope.launch {
            dao.delete(task.value!!)
            _navigateToList.value = true
        }
    }

    //EditTaskFragment會在完成巡覽時，呼叫此方法，將_navigateToList.value設回預設
    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}