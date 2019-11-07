package byc.app.tday.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.dataClass.ToDay
import org.w3c.dom.Text

class ToDayListAdapter(val toDayList: MutableList<ToDay>): RecyclerView.Adapter<ToDayListAdapter.ToDayListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDayListViewHolder = ToDayListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.to_day_item, parent, false))

    override fun getItemCount(): Int = toDayList.size

    override fun onBindViewHolder(holder: ToDayListViewHolder, position: Int) {
        holder.toDayDate.text = toDayList[position].toDay
        holder.toDayWillDoWorkComment.text = toDayList[position].toDayWillDoWork.toDayWillDoWork
        holder.toDayWillDoStudyComment.text = toDayList[position].toDayWillDoWork.toDayWillDoStudy
        val toDayMoneyListAdapter = ToDayMoneyListAdapter(toDayList)
        holder.toDayWorkAndMoneyList.adapter = toDayMoneyListAdapter
    }

    class ToDayListViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val toDayDate = item.findViewById<TextView>(R.id.toDayDate)
        val toDayWillDoWorkComment = item.findViewById<TextView>(R.id.toDayWillDoWorkComment)
        val toDayWillDoStudyComment = item.findViewById<TextView>(R.id.toDayWillDoStudyComment)
        val toDayWorkAndMoneyList = item.findViewById<RecyclerView>(R.id.toDayWorkAndMoneyList)
    }
}