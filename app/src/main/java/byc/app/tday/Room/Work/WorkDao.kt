package byc.app.tday.Room.Work

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkDao{

    @Query("DELETE FROM work_model")
    suspend fun deleteAllWorkModel()

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