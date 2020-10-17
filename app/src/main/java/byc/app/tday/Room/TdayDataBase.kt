package byc.app.tday.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//한번만 객체를 할당 하기 위해 싱글톤 선언
@Database(entities = arrayOf(WorkModel::class, MoneyModel::class), version = 1, exportSchema = false)
public abstract class TdayDataBase : RoomDatabase() {

    //CallBack에서 데이터를 사용하기 위해
    abstract fun workDao(): WorkDao
    abstract fun moneyDao(): MoneyDao
    abstract fun workWithMoneyDao(): WorkWithMoneyDao

    companion object {
        @Volatile
        private var INSTANCE: TdayDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TdayDataBase {
            val tempinstance = INSTANCE
            if (tempinstance != null) {
                return tempinstance
            }
            //여러 쓰레드가 접급하지 않게 synchronized 사용
            synchronized(this) {
                val instance = Room.databaseBuilder(    //databaseBuilder로 인스턴스를 생성합
                    context.applicationContext,
                    TdayDataBase::class.java,
                    "tday_database"
                )
                .addCallback(TdayDatabaseCallback(scope))
                .build()                            //Repository에서 호출하여 사용
                INSTANCE = instance
                return instance
            }
        }
    }

    //DataCallBack Event
    class TdayDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback()
    {
        //OnOpen DB를 처음 열때 생기는 이벤트(화면에 처음 들어갈때 생기는 이벤트
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { tdayDataBase ->
                scope.launch {
                    populateDatabase(tdayDataBase.workDao())
                }
            }
        }

        suspend fun populateDatabase(workDao: WorkDao) {
        }
    }
}
