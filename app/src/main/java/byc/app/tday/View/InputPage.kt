package byc.app.tday.View

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import byc.app.tday.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_input_page.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.hours
import kotlin.time.minutes

class InputPage : AppCompatActivity() {

    companion object {
        const val WORK_CATEGORY = "workCategory"
        const val WORK_MEMO = "workMemo"
        const val WORK_USE_TIME = "workUseTime"
        const val WORK_DATA_ARRAY = "workDataARRAY"
    }

    val workCategory: List<String> = listOf("운동", "취미", "공부", "식사", "일", "게임", "독서")
    var categoryData = "운동"

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
                when(position){
                    0 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                    1 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                    2 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                    3 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                    4 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                    5 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                    6 -> {
                        categoryData = workCategory.get(position)
                        workCategorySpinner.setSelection(position)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        todayAddButton.setOnClickListener { view ->
            val replyIntent = Intent()
            replyIntent.putExtra(WORK_DATA_ARRAY, arrayListOf(workContent.text.toString(), startTextView.text.toString(), categoryData))
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
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
}