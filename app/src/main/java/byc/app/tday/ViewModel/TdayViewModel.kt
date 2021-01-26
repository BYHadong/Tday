package byc.app.tday.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import byc.app.tday.Room.TdayDataBase
import byc.app.tday.Room.TdayRepository
import byc.app.tday.Room.WorkWithMoneyDao
import byc.app.tday.Room.WorkWithMoneyModel

class TdayViewModel(application: Application): AndroidViewModel(application){
    private val repository: TdayRepository
    val tdayData: LiveData<List<WorkWithMoneyModel>>

    init {
        val tdayDao = TdayDataBase.getDatabase(application, viewModelScope).workWithMoneyDao()
        repository = TdayRepository(tdayDao)
        tdayData = repository.tdayData
    }
}