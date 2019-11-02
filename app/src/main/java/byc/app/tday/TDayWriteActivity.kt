package byc.app.tday

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import byc.app.tday.dataClass.ToDay
import byc.app.tday.dataClass.ToDayMoney
import byc.app.tday.dataClass.ToDayWillDoWork
import byc.app.tday.dataClass.ToDayWork
import kotlinx.android.synthetic.main.activity_tday_write.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TDayWriteActivity : AppCompatActivity() {

    lateinit var toDayOkButton: Button
    lateinit var toDayWillDoWorkComment: EditText
    lateinit var toDayWillDoWorkStudy: EditText
    lateinit var toDayWillDoWorkStartTime: EditText
    lateinit var toDayWillDoWorkFinishTime: EditText
    lateinit var toDayWillDoWork: EditText
    lateinit var toDayUseMoneyCategory: Spinner
    lateinit var toDayUseMoney: EditText
    lateinit var toDayUseMoneyMemo: EditText

    val tDay: ArrayList<ToDay> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tday_write)

        toDayWillDoWorkComment = findViewById(R.id.toDayWillDoWorkComment_WriteLayout)
        toDayWillDoWorkStudy = findViewById(R.id.toDayWillDoWorkStudy_WriteLayout)
        toDayWillDoWorkStartTime = findViewById(R.id.toDayWorkStartTime_WriteLayout)
        toDayWillDoWorkFinishTime = findViewById(R.id.toDayWorkFinishTime_WriteLayout)
        toDayWillDoWork = findViewById(R.id.toDayWillDoWork_WriteLayout)
        toDayUseMoneyCategory = findViewById(R.id.toDayUseMoneyCategory_WriteLayout)
        toDayUseMoney = findViewById(R.id.toDayUseMoney_WriteLayout)
        toDayUseMoneyMemo = findViewById(R.id.toDayUseMoneyMemo_WriteLayout)
        toDayOkButton = findViewById(R.id.toDayOkButton)


        toDayOkButton.setOnClickListener {
            //데이터 받아오기
            //데이터가 아직은 없어도 ㄱㅊ
            val toDayComment = toDayWillDoWorkComment.text.toString()
            val toDayStudy = toDayWillDoWorkStudy.text.toString()
            val toDayWork = toDayWillDoWork.text.toString()
            val toDayStartTime = toDayWillDoWorkStartTime.text.toString()
            val toDayFinishTime = toDayWillDoWorkFinishTime.text.toString()
            val toDayUseMoneyCategory = toDayUseMoneyCategory.selectedItem.toString()
            val toDayUseMoney = toDayUseMoney.text.toString()
            val todayUseMoneyMemo = toDayUseMoneyMemo.text.toString()

            //데이터 값 넣기
            val toDayWillDoWorkGroup = ToDayWillDoWork(toDayWork, toDayStudy)
            val toDayWorkGroup = ToDayWork(toDayComment, toDayStartTime, toDayFinishTime)
            val toDayMoneyGroup = ToDayMoney(toDayUseMoney.toInt(), toDayUseMoneyCategory, todayUseMoneyMemo)
            
            //초기화
            val tDayData = ToDay(
                "${GetDayOfWeek()} ${SimpleDateFormat(
                    "yyyy년 MM월 dd일",
                    Locale.KOREA
                ).format(Date(System.currentTimeMillis()))}",
                toDayWillDoWorkGroup,
                toDayWorkGroup,
                toDayMoneyGroup
            )
            tDay.add(tDayData)
            //DataBase 에 추가
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("flag", true)
            finish()
        }

    }

    fun GetDayOfWeek(): String {
        val week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        when (week) {
            1 -> {
                return "일요일"
            }
            2 -> {
                return "월요일"
            }
            3 -> {
                return "화요일"
            }
            4 -> {
                return "수요일"
            }
            5 -> {
                return "목요일"
            }
            6 -> {
                return "금요일"
            }
            7 -> {
                return "토요일"
            }
            else -> {
                return "요일을 측정 할 수 없음"
            }
        }
    }
}
