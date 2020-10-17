package byc.app.tday.Page

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import byc.app.tday.R
import kotlinx.android.synthetic.main.activity_input_page.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InputPage : AppCompatActivity() {

    companion object {
        const val WORK_DATA_ARRAY = "workData"
        const val MONEY_DATA_ARRAY = "moneyData"
    }

    val workCategory: List<String> = listOf("운동", "취미", "공부", "식사", "일", "게임", "독서")
    var categoryData = "운동"
    val useMoney = ArrayList<EditText>()
    val useMoneyMemo = ArrayList<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_page)

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, workCategory)
        workCategorySpinner.adapter = spinnerAdapter
        workCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categoryData = workCategory.get(position)
                workCategorySpinner.setSelection(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        todayAddButton.setOnClickListener { view ->
            val replyIntent = Intent()
            //0: StartTime, 1: FinishTime, 2: Category, 3: WorkContent, 4: userPosition, 5: useMoney, 6: useMoneyMemo
            replyIntent.putExtra(WORK_DATA_ARRAY, arrayListOf(startTextView.text.toString(), finishTextView.text.toString(), categoryData, workContent.text.toString(), userPosition.text.toString()))
            if(useMoney.size != 0)
                replyIntent.putExtra(MONEY_DATA_ARRAY, arrayListOf(UseMoneyFormat(useMoney), UseMoneyFormat(useMoneyMemo)))
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }

        addMoneyBtn.setOnClickListener { view ->
            val useMoneyExv = EditText(this)
            val useMoneyMemoExv = EditText(this)
            useMoneyExv.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            useMoneyMemoExv.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            inputPageUseMoneyLayout.addView(useMoneyExv)
            inputPageUseMoneyLayout.addView(useMoneyMemoExv)
            useMoney.add(useMoneyExv)
            useMoneyMemo.add(useMoneyMemoExv)
        }

        val nowTime = SimpleDateFormat("HH:mm").format(Calendar.getInstance().time)
        val timeLisener1 = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val timeData = "${hourOfDay}:${minute}"
            startTextView.text = timeData
        }
        val timeLisener2 = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val timeData = "${hourOfDay}:${minute}"
            finishTextView.text = timeData
        }

        startTextView.setOnClickListener { view ->
            val timeSet = TimePickerDialog(
                this,
                timeLisener1,
                nowTime.substring(0, 1).toInt(),
                nowTime.substring(3, 4).toInt(),
                true
            )
            timeSet.show()
        }
        finishTextView.setOnClickListener { view ->
            val timeSet = TimePickerDialog(
                this,
                timeLisener2,
                nowTime.substring(0, 1).toInt(),
                nowTime.substring(3, 4).toInt(),
                true
            )
            timeSet.show()
        }
    }

    fun UseMoneyFormat(arrayText: ArrayList<EditText>): String{
        val stringBuffer = StringBuffer()
        arrayText.forEach { editText ->
            editText.let {
                stringBuffer.append("${editText.text},")
            }
        }
        return stringBuffer.toString()
    }
}