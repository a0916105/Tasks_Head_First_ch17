package tw.idv.jew.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")   //設定資料表的名稱
data class Task(
    @PrimaryKey(autoGenerate = true)    //設定主鍵，並自動產生
    var taskId: Long = 0L,
    @ColumnInfo(name = "task_name") //覆寫資料表的欄位名稱
    var taskName: String = "",
    @ColumnInfo(name = "task_done") //覆寫(重新命名)資料表的欄位名稱
    var taskDone: Boolean = false
)
