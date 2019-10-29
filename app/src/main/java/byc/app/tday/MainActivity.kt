package byc.app.tday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tDayButton : FloatingActionButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tDayButton = findViewById(R.id.tdayButton)
        tDayButton.setOnClickListener {
            startActivity(Intent(applicationContext, TDayWriteActivity::class.java))
        }

    }
}
