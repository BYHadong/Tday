package byc.app.tday.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import byc.app.tday.Room.Work.WorkModel
import byc.app.tday.ViewModel.WorkViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tday_main.*


class TdayMain : AppCompatActivity() {

    private lateinit var workViewModel: WorkViewModel //이미 생성된 ViewModel 인스턴스가 있다면 이를 반환 하여 메모리 낭비를 줄여줌

    private var isMainFabButtonClicked = false
    val newWorkActivityRequesCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tday_main)

        val todoListAdapter = TodoListAdapter(this)
        toDoList.adapter = todoListAdapter
        toDoList.layoutManager = LinearLayoutManager(this)

        workViewModel = ViewModelProvider(this).get(WorkViewModel::class.java)
        workViewModel.workData.observe(this, Observer { work ->
            work?.let {
                todoListAdapter.setTodoList(work)
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
        when(fabButton.id){
            inputPage.id -> {
                fabButton.setOnClickListener { view ->
                    startActivityForResult(Intent(this, InputPage::class.java), newWorkActivityRequesCode)
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

    fun FabButtonLayoutParmsChange(fabButton: FloatingActionButton, showing: Boolean) {
        if (showing) {
            FabShowAnimation(fabButton)
            fabButton.isClickable = true
        } else {
            FabOutAnimation((fabButton))
            fabButton.isClickable = false
        }
    }

    fun FabShowAnimation(fabButton: FloatingActionButton) {
        Toast.makeText(this, "Show Event Start", Toast.LENGTH_SHORT).show()
        fabButton.visibility = View.VISIBLE
        fabButton.alpha = 0f
        fabButton.translationY = 0F
        fabButton.animate()
            .setDuration(200)
            .translationY(-(fabButton.height.toFloat()))
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                }
            })
            .alpha(1f)
            .start()
    }

    fun FabOutAnimation(fabButton: FloatingActionButton) {
        Toast.makeText(this, "Show Event End", Toast.LENGTH_SHORT).show()
        fabButton.visibility = View.VISIBLE
        fabButton.alpha = 1f
        fabButton.translationY = -(fabButton.height.toFloat())
        fabButton.animate()
            .setDuration(200)
            .translationY(0F)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    fabButton.visibility = View.GONE
                    super.onAnimationEnd(animation)
                }
            }).alpha(0f)
            .start()
    }

    fun FabAnimationInit(fabButton: FloatingActionButton) {
        fabButton.visibility = View.GONE
    }

    fun RotateFab(fabButton: FloatingActionButton, rotate: Boolean): Boolean {
        fabButton.animate().setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                }
            })
            .rotation(if (rotate) 135f else 0f)
        return rotate
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newWorkActivityRequesCode && resultCode == Activity.RESULT_OK){
            data?.getStringArrayListExtra(InputPage.WORK_DATA_ARRAY)?.let {
                val work = WorkModel(work_memo = it[0], use_work_time = it[1], category =  it[2])
                workViewModel.WorkDataInsert(work)
                val workDatas = workViewModel.workData
                Log.d("work Insert", "응답있음(Room) 카테고리 : ${it[0]}, 사용 시간 : ${it[1]}, 한일 : ${it[2]} 앙앙 사이즈 : ${workDatas.observe(this, Observer { Log.d("workSize",it.size.toString()) })}")
                Snackbar.make(window.decorView.rootView, "응답있음(Room) 카테고리 : ${it[0]}, 사용 시간 : ${it[1]}, 한일 : ${it[2]} 앙앙 사이즈 : ${workDatas.observe(this, Observer { Log.d("workSize",it.size.toString()) })}", Snackbar.LENGTH_SHORT).show()
            }
        }
        else
        {
            Snackbar.make(window.decorView.rootView, "응답없음(Room)", Snackbar.LENGTH_SHORT).show()
        }
    }
}