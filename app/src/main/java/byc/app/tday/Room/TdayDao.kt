package byc.app.tday.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import byc.app.tday.Room.WorkModel

@Dao
interface WorkDao{

    @Query("DELETE FROM work_model")
    suspend fun deleteAllWorkData()

    @Query("SELECT * FROM work_model")
    fun workDataGetAll(): LiveData<List<WorkModel>>

    //중복값 대처 REPLACE, IGNORE, ABORT, FAIL, ROLLBACK 등의 속성 들이 있음
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun workDataInsert(workModel: WorkModel)

    @Update
    suspend fun workDataUpdate(workModel: WorkModel)

    @Delete
    suspend fun workDataDelete(workModel: WorkModel)
}

@Dao
interface MoneyDao{
    @Query("DELETE FROM money_table")
    suspend fun deleteAllMoneyData()

    @Query("SELECT * FROM money_table")
    fun moneyDataGetAll() : LiveData<List<MoneyModel>>

    //중복값 대처 REPLACE, IGNORE, ABORT, FAIL, ROLLBACK 등의 속성 들이 있음
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun moneyDataInsert(moneyModel: MoneyModel)

    @Update
    suspend fun moneyDataUpdate(moneyModel: MoneyModel)

    @Delete
    suspend fun moneyDataDelete(moneyModel: MoneyModel)
}

@Dao
interface WorkWithMoneyDao {
    //2개의 쿼리가 실행이 되기 때문에 @Transaction을 통해 전체 작업이 원자적으로 실행되도록 해줌
    @Transaction
    @Query("SELECT * FROM work_model")
    fun tdayDataGetAll(): LiveData<List<WorkWithMoneyModel>>
}