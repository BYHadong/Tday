package byc.app.tday.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.dataClass.ToDay

class ToDayMoneyListAdapter(val toDayMoneyList: List<ToDay>): RecyclerView.Adapter<ToDayMoneyListAdapter.TodayMoneyListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayMoneyListViewHolder = TodayMoneyListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.to_day_work_money_item, parent, false))

    override fun getItemCount(): Int = toDayMoneyList.size

    override fun onBindViewHolder(holder: TodayMoneyListViewHolder, position: Int) {
        val toDayWorkList = toDayMoneyList[position].toDayWork
        val toDayMoneyList = toDayMoneyList[position].toDayMoney
        holder.toDayStartTime.text = toDayWorkList.toDayWorkStartTime
        holder.toDayFinishTime.text = toDayWorkList.toDayWorkFinishTime
        holder.toDayWork.text = toDayWorkList.toDayWork
        holder.toDayMoneyCategory.text = toDayMoneyList.toDayUseMoneyCategory
        holder.toDayMoney.text = toDayMoneyList.toDayUseMoney
        holder.toDayMoneyMemo.text = toDayMoneyList.toDayUseMoneyMemo
    }

    class TodayMoneyListViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val toDayWork = item.findViewById<TextView>(R.id.toDayWork)
        val toDayStartTime = item.findViewById<TextView>(R.id.toDayWorkStartTime)
        val toDayFinishTime = item.findViewById<TextView>(R.id.toDayWorkFinishTime)
        val toDayMoneyCategory = item.findViewById<TextView>(R.id.toDayUseMoneyCategory)
        val toDayMoney = item.findViewById<TextView>(R.id.toDayUseMoney)
        val toDayMoneyMemo = item.findViewById<TextView>(R.id.toDayUseMoneyMemo)
    }
}