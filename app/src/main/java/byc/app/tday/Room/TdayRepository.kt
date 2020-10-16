package byc.app.tday.Room

import androidx.lifecycle.LiveData

/*
 * Repository를 만드는 이유
 * Repository를 만들어 Room에는 별도의 쓰레드로 접근하기 위함
 * 메인 쓰래드로 접근하면 쓰레드 오류가 발생함
 * 쓰레드 오류) Cannot access database on the main thread since it may potentially lock the UI for a long period of time. (메인 UI 화면이 오랫동안 멈춰있을 수도 있기 때문에, 메인 쓰레드에서는 데이터베이스에 접근할 수 없습니다.)
 */
class WorkRepository(private val wordDao: WorkDao){
    val workData: LiveData<List<WorkModel>> = wordDao.workDataGetAll()

    suspend fun workDataInsert(workModel: WorkModel){
        wordDao.workDataInsert(workModel)
    }

    suspend fun workDataUpdate(workModel: WorkModel){
        wordDao.workDataUpdate(workModel)
    }

    suspend fun workDataDelete(workModel: WorkModel){
        wordDao.workDataDelete(workModel)
    }
}

class MoneyRepository(private val moneyDao: MoneyDao){
    val moneyData: LiveData<List<MoneyModel>> = moneyDao.moneyDataGetAll()

    suspend fun moneyDataInsert(moneyModel: MoneyModel){
        moneyDao.moneyDataInsert(moneyModel)
    }

    suspend fun moneyDataUpdate(moneyModel: MoneyModel){
        moneyDao.moneyDataUpdate(moneyModel)
    }

    suspend fun moneyDataDelete(moneyModel: MoneyModel){
        moneyDao.moneyDataDelete(moneyModel)
    }
}

class TdayRepository(private val tdayDao:WorkWithMoneyDao){
    val tdayData: LiveData<List<WorkWithMoneyModel>> = tdayDao.tdayDataGetAll()
}