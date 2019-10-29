package byc.app.tday.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.dataClass.ToDayMoney

class ToDayMoneyListAdapter(private val toDayMoneyList: List<ToDayMoney>): RecyclerView.Adapter<ToDayMoneyListAdapter.TodayMoneyListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayMoneyListViewHolder = TodayMoneyListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.to_day_work_money_item, parent, false))

    override fun getItemCount(): Int = toDayMoneyList.size

    override fun onBindViewHolder(holder: TodayMoneyListViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class TodayMoneyListViewHolder(item: View) : RecyclerView.ViewHolder(item){

    }
}