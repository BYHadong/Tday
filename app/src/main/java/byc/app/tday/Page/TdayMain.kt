package byc.app.tday.Page

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import byc.app.tday.Model.adapter.TodoListAdapter
import byc.app.tday.R
import byc.app.tday.Room.MoneyModel
import byc.app.tday.Room.WorkModel
import byc.app.tday.ViewModel.MoneyViewModel
import byc.app.tday.ViewModel.TdayViewModel
import byc.app.tday.ViewModel.WorkViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tday_main.*


class TdayMain : AppCompatActivity() {

    private lateinit var workViewModel: WorkViewModel //이미 생성된 ViewModel 인스턴스가 있다면 이를 반환 하여 메모리 낭비를 줄여줌
    private lateinit var tdayViewModel: TdayViewModel
    private lateinit var moneyViewModel: MoneyViewModel

    private var isMainFabButtonClicked = false
    val newWorkActivityRequesCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tday_main)

        val todoListAdapter = TodoListAdapter(this)
        toDoList.adapter = todoListAdapter
        toDoList.layoutManager = LinearLayoutManager(this)

//        workViewModel = ViewModelProvider(this).get(WorkViewModel::class.java)
//        workViewModel.workData.observe(this, Observer { work ->
//            work?.let {
//                todoListAdapter.setTodoList(work)
//            }
//        })

        workViewModel = ViewModelProvider(this).get(WorkViewModel::class.java)
        moneyViewModel = ViewModelProvider(this).get(MoneyViewModel::class.java)
        tdayViewModel = ViewModelProvider(this).get(TdayViewModel::class.java)
        tdayViewModel.tdayData.observe(this, Observer { tdayData ->
            tdayData.let {
                todoListAdapter.setTodoList(tdayData)
            }
        })

        FabClickEventAdd(inputPage)
        FabClickEventAdd(listPage)
        FabClickEventAdd(chartPage)
        FabClickEventAdd(todoPage)

        mainFabButton.setOnClickListener { view ->
            isMainFabButtonClicked = RotateFab(mainFabButton, !isMainFabButtonClicked)
            if (isMainFabButtonClicked) {
                FabButtonLayoutParmsChange(inputPage, true)
                FabButtonLayoutParmsChange(listPage, true)
                FabButtonLayoutParmsChange(chartPage, true)
                FabButtonLayoutParmsChange(todoPage, true)
            } else {
                FabButtonLayoutParmsChange(inputPage, false)
                FabButtonLayoutParmsChange(listPage, false)
                FabButtonLayoutParmsChange(chartPage, false)
                FabButtonLayoutParmsChange(todoPage, false)
            }
        }
    }

    fun FabClickEventAdd(fabButton: FloatingActionButton) {
        FabAnimationInit(fabButton)
        when (fabButton.id) {
            inputPage.id -> {
                fabButton.setOnClickListener { view ->
                    startActivityForResult(
                        Intent(this, InputPage::class.java),
                        newWorkActivityRequesCode
                    )
                }
            }
            listPage.id -> {
                fabButton.setOnClickListener { view ->
                    Snackbar.make(view, "listPage", Snackbar.LENGTH_SHORT).show()
                }
            }
            chartPage.id -> {
                fabButton.setOnClickListener { view ->
                    Snackbar.make(view, "chartPage", Snackbar.LENGTH_SHORT).show()
                }
            }
            todoPage.id -> {
                fabButton.setOnClickListener { view ->
                    Snackbar.make(view, "todoPage", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun FabButtonHeightCal(fabButton: FloatingActionButton): Float {
        when (fabButton.id) {
            inputPage.id -> {
                return (-(fabButton.height.toFloat()) * 1.5F)
            }
            listPage.id -> {
                return (-(fabButton.height.toFloat()) * 3.0F)
            }
            chartPage.id -> {
                return (-(fabButton.height.toFloat()) * 4.5F)
            }
            todoPage.id -> {
                return (-(fabButton.height.toFloat()) * 6.0F)
            }
            else -> {
                return 0F
            }
        }
    }

    fun FabButtonLayoutParmsChange(fabButton: FloatingActionButton, showing: Boolean) {
        val fabButtonAni = FabButtonHeightCal(fabButton)
        if (showing) {
            FabShowAnimation(fabButton, fabButtonAni)
            fabButton.isClickable = true
        } else {
            FabOutAnimation(fabButton, fabButtonAni)
            fabButton.isClickable = false
        }
    }

    fun FabShowAnimation(fabButton: FloatingActionButton, height: Float) {
        Toast.makeText(this, "Show Event Start", Toast.LENGTH_SHORT).show()
        fabButton.visibility = View.VISIBLE
        fabButton.alpha = 0f
        fabButton.translationY = 0F
        fabButton.animate()
            .setDuration(200)
            .translationY(height)
            .alpha(1f)
            .start()
    }

    fun FabOutAnimation(fabButton: FloatingActionButton, height: Float) {
        fabButton.visibility = View.VISIBLE
        fabButton.alpha = 1f
        fabButton.translationY = height
        fabButton.animate()
            .setDuration(200)
            .translationY(0F)
            .alpha(0f)
            .start()
    }

    fun FabAnimationInit(fabButton: FloatingActionButton) {
        fabButton.visibility = View.GONE
        fabButton.alpha = 0F
        fabButton.translationY = 0F
    }

    fun RotateFab(fabButton: FloatingActionButton, rotate: Boolean): Boolean {
        fabButton.animate().setDuration(200).rotation(if (rotate) 135f else 0f).start()
        return rotate
    }

    //DB에 DATA insert
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWorkActivityRequesCode && resultCode == Activity.RESULT_OK) {
            data?.getStringArrayListExtra(InputPage.WORK_DATA_ARRAY)?.let { tdayData ->
                val work = WorkModel(
                    category = tdayData.get(2),
                    workStartTime = tdayData.get(0),
                    workFinishTime = tdayData.get(1),
                    workMemo = tdayData.get(3),
                    workPosition = tdayData.get(4)
                )
                workViewModel.WorkDataInsert(work)
                var workIdData: Long? = 0L
                workViewModel.workData.observe(
                    this,
                    Observer { workID -> workIdData = workID.get(workID.lastIndex).id })
                val money =
                    MoneyModel(value = tdayData.get(5), memo = tdayData.get(6), workId = workIdData)
                moneyViewModel.MoneyDataInsert(money)

                val tdayDatas = tdayViewModel.tdayData
                tdayDatas.observe(this, Observer { tdayVal ->
                    run {
                        val tdata = tdayVal[tdayVal.size - 1]
                        Log.d(
                            "Data Insert",
                            "work id : ${tdata.workModel.id}, category : ${tdata.workModel.category}, startTime : ${tdata.workModel.workStartTime}, finishTime : ${tdata.workModel.workFinishTime}" +
                                    ", workMemo : ${tdata.workModel.workMemo}, workPosition : ${tdata.workModel.workPosition}, moneyUse : ${tdata.moneyList[tdata.moneyList.size - 1].value}, moneyMemo : ${tdata.moneyList[tdata.moneyList.size - 1].memo}"
                        )
                    }
                })
            }
        } else {
            Snackbar.make(window.decorView.rootView, "응답없음(Room)", Snackbar.LENGTH_SHORT).show()
        }
    }
}