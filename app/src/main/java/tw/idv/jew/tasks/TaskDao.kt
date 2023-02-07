package tw.idv.jew.tasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao    //告訴Room這個介面是用來存取資料的
interface TaskDao {
    //未使用live data的資料使用suspend將其轉成協同程序（在背景運行，且可暫停）
    @Insert //插入
    suspend fun insert(task: Task)
    @Update //修改
    suspend fun update(task: Task)
    @Delete //刪除
    suspend fun delete(task: Task)

    //查詢
    @Query("SELECT * FROM task_table WHERE taskId = :key")
    fun get(key: Long): LiveData<Task>
    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>
}