package tw.idv.jew.tasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao   //告訴Room你想要使用TaskDao指定的資料存取方法

    companion object {  //將getInstance()放入companion物件：不需先建立TaskDatabase實體就可以呼叫它
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {   //取得資料庫實體的方法
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) { //如果資料庫不存在，getInstance()會建構它
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}