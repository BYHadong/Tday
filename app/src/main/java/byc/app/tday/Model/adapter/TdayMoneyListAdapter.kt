package byc.app.tday.Model.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import kotlinx.android.synthetic.main.todo_list_money_item.view.*

class TdayMoneyListAdapter : RecyclerView.Adapter<TdayMoneyListAdapter.TdayMoneyViewHolder>() {
    inner class TdayMoneyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val workUseMoney: TextView = view.findViewById(R.id.useMoney)
        val workUseMoneyMemo: TextView = view.findViewById(R.id.useMoneyMemo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TdayMoneyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TdayMoneyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}