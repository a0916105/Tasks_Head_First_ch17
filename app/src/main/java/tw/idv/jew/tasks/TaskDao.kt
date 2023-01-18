package tw.idv.jew.tasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao    //告訴Room這個介面是用來存取資料的
interface TaskDao {
    @Insert //插入
    fun insert(task: Task)
    @Update //修改
    fun update(task: Task)
    @Delete //刪除
    fun delete(task: Task)

    //查詢
    @Query("SELECT * FROM task_table WHERE taskId = :key")
    fun get(key: Long): LiveData<Task>
    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>
}