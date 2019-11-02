package byc.app.tday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import byc.app.tday.Adapter.ToDayListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tDayButton : FloatingActionButton;
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tDayButton = findViewById(R.id.tdayButton)
        tDayButton.setOnClickListener {
            val intent = Intent(applicationContext, TDayWriteActivity::class.java)
            startActivity(intent)
            flag = intent.getBooleanExtra("flag", false)
            Log.d("flagSearch", flag.toString())
            if(flag)
                Toast.makeText(this, "test", Toast.LENGTH_LONG).show()
        }
    }
}
