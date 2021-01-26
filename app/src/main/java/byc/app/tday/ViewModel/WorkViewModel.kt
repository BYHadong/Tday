package byc.app.tday.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import byc.app.tday.Room.TdayDataBase
import byc.app.tday.Room.WorkRepository
import byc.app.tday.Room.WorkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkViewModel(application: Application) : AndroidViewModel(application){
    private val repository: WorkRepository
    val workData: LiveData<List<WorkModel>>

    init {
        val workDao = TdayDataBase.getDatabase(application, viewModelScope).workDao()
        repository = WorkRepository(workDao)
        workData = repository.workData
    }

    fun WorkDataInsert(workModel: WorkModel) = viewModelScope.launch(Dispatchers.IO){
        repository.workDataInsert(workModel)
    }

    fun WorkDataUpdate(workModel: WorkModel) = viewModelScope.launch(Dispatchers.IO){
        repository.workDataUpdate(workModel)
    }

    fun WorkDataDelete(workModel: WorkModel) = viewModelScope.launch(Dispatchers.IO){
        repository.workDataDelete(workModel)
    }
}