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
import byc.app.tday.Model.adapter.TdayListAdapter
import byc.app.tday.Model.adapter.TdayMoneyListAdapter
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

        val todoListAdapter = TdayListAdapter(this)
        toDoList.adapter = todoListAdapter
        toDoList.layoutManager = LinearLayoutManager(this)



        workViewModel = ViewModelProvider(this).get(WorkViewModel::class.java)
        moneyViewModel = ViewModelProvider(this).get(MoneyViewModel::class.java)
        moneyViewModel.moneyData.observe(this, Observer { moneyData ->
            moneyData.let { money ->
                Log.d("MoneyData", "moneySize ${money.size}")
                for (data in money) {
                    Log.d(
                        "MoneyData data",
                        "data Value : ${data.value} Memo : ${data.memo} MoneyId : ${data.moneyId} WorkId : ${data.workId}"
                    )
                }
            }
        });
        tdayViewModel = ViewModelProvider(this).get(TdayViewModel::class.java)
        tdayViewModel.tdayData.observe(this, Observer { tdayData ->
            tdayData.let {
                todoListAdapter.setTodoList(tdayData)
            }
        })

        FabClickEventAdd(inputPageMainLayout)
        FabClickEventAdd(listPage)
        FabClickEventAdd(chartPage)
        FabClickEventAdd(todoPage)

        mainFabButton.setOnClickListener { view ->
            isMainFabButtonClicked = RotateFab(mainFabButton, !isMainFabButtonClicked)
            if (isMainFabButtonClicked) {
                FabButtonLayoutParmsChange(inputPageMainLayout, true)
                FabButtonLayoutParmsChange(listPage, true)
                FabButtonLayoutParmsChange(chartPage, true)
                FabButtonLayoutParmsChange(todoPage, true)
            } else {
                FabButtonLayoutParmsChange(inputPageMainLayout, false)
                FabButtonLayoutParmsChange(listPage, false)
                FabButtonLayoutParmsChange(chartPage, false)
                FabButtonLayoutParmsChange(todoPage, false)
            }
        }
    }

    fun FabClickEventAdd(fabButton: FloatingActionButton) {
        FabAnimationInit(fabButton)
        when (fabButton.id) {
            inputPageMainLayout.id -> {
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
            inputPageMainLayout.id -> {
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
            data?.getStringArrayListExtra(InputPage.WORK_DATA_ARRAY)?.let { workdData ->
                //0: StartTime, 1: FinishTime, 2: Category, 3: WorkMemo, 4: userPosition,
                val work = WorkModel(
                    category = workdData.get(2),
                    workStartTime = workdData.get(0),
                    workFinishTime = workdData.get(1),
                    workMemo = workdData.get(3),
                    workPosition = workdData.get(4)
                )
                workViewModel.WorkDataInsert(work)
            }

//            0: useMoney, 1: useMoneyMemo
            data?.getStringArrayListExtra(InputPage.MONEY_DATA_ARRAY)?.let { moneyData ->
                val money = MoneyModel(value = moneyData.get(0), memo = moneyData.get(1))
                moneyViewModel.MoneyDataInsert(money)
            }
        } else {
            Snackbar.make(window.decorView.rootView, "응답없음(Room)", Snackbar.LENGTH_SHORT).show()
        }
    }
}