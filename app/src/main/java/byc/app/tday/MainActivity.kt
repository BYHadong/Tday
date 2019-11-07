package byc.app.tday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import byc.app.tday.Adapter.ToDayListAdapter
import byc.app.tday.DataBase.ToDayDataBaseHelper
import byc.app.tday.dataClass.ToDay
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var toDayDataList: MutableList<ToDay>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tDayButton = findViewById<FloatingActionButton>(R.id.tdayButton)
        tDayButton.setOnClickListener {
            val intent = Intent(applicationContext, TDayWriteActivity::class.java)
            startActivity(intent)
            finish()
        }

        val dataBase = ToDayDataBaseHelper(this)
        toDayDataList = dataBase.GetToDayDataAll()
        if(toDayDataList != null){
            val toDayListAdapter = ToDayListAdapter(toDayDataList!!)
            toDayList.adapter = toDayListAdapter
            toDayListAdapter.notifyDataSetChanged()
        }
        else {
           Snackbar.make(window.decorView.rootView, "데이터가 없습니다.", Snackbar.LENGTH_LONG).show();
        }
    }
}

