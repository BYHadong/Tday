package byc.app.tday.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import byc.app.tday.R
import byc.app.tday.ViewModel.WorkViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tday_main.*


class TdayMain : AppCompatActivity() {

    private lateinit var workViewModel: WorkViewModel //이미 생성된 ViewModel 인스턴스가 있다면 이를 반환 하여 메모리 낭비를 줄여줌

    private var isMainFabButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tday_main)

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
        fabButton.setOnClickListener { view ->
            Snackbar.make(view, "${fabButton.id} Button Click", Snackbar.LENGTH_SHORT).show()
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
        fabButton.visibility = View.VISIBLE
        fabButton.alpha = 0f
        fabButton.translationY = -fabButton.height.toFloat()
        fabButton.animate()
            .setDuration(200)
            .translationY(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                }
            })
            .alpha(1f)
            .start()
    }

    fun FabOutAnimation(fabButton: FloatingActionButton) {
        fabButton.visibility = View.VISIBLE
        fabButton.alpha = 1f
        fabButton.translationY = 0f
        fabButton.animate()
            .setDuration(200)
            .translationY(-fabButton.height.toFloat())
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
        fabButton.translationY = fabButton.height.toFloat()
        fabButton.alpha = 0f
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
}