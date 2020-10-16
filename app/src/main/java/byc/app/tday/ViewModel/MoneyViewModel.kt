package byc.app.tday.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import byc.app.tday.Room.MoneyModel
import byc.app.tday.Room.MoneyRepository
import byc.app.tday.Room.TdayDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyViewModel(application: Application): AndroidViewModel(application) {
    private  val repository: MoneyRepository
    val moneyData: LiveData<List<MoneyModel>>

    init {
        val moneyDao =TdayDataBase.getDatabase(application, viewModelScope).moneyDao()
        repository = MoneyRepository(moneyDao)
        moneyData = repository.moneyData
    }

    fun MoneyDataInsert(moneyModel: MoneyModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.moneyDataInsert(moneyModel)
    }

    fun MoneyDataUpdate(moneyModel: MoneyModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.moneyDataUpdate(moneyModel)
    }

    fun MoneyDataDelete(moneyModel: MoneyModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.moneyDataDelete(moneyModel)
    }
}