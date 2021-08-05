package byc.app.tday.Model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.Room.MoneyModel
import byc.app.tday.ViewModel.MoneyViewModel
import kotlinx.android.synthetic.main.todo_list_money_item.view.*

class TdayMoneyListAdapter internal constructor(context: Context, val moneyList: List<MoneyModel>) :
    RecyclerView.Adapter<TdayMoneyListAdapter.TdayMoneyViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    inner class TdayMoneyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val workUseMoney: TextView = view.findViewById(R.id.useMoney)
        val workUseMoneyMemo: TextView = view.findViewById(R.id.useMoneyMemo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TdayMoneyViewHolder =
        TdayMoneyViewHolder(inflater.inflate(R.layout.todo_list_money_item, parent, false))

    override fun onBindViewHolder(holder: TdayMoneyViewHolder, position: Int) {
        val moneyData = moneyList[position]
        holder.workUseMoney.text = moneyData.value
        holder.workUseMoneyMemo.text = moneyData.memo
    }

    override fun getItemCount(): Int = moneyList.size
}