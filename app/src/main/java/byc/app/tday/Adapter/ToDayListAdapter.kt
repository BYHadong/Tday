package byc.app.tday.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.dataClass.ToDay

class ToDayListAdapter(private val toDayList: List<ToDay>): RecyclerView.Adapter<ToDayListAdapter.ToDayListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDayListViewHolder = ToDayListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.to_day_item, parent, false))

    override fun getItemCount(): Int = toDayList.size

    override fun onBindViewHolder(holder: ToDayListViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ToDayListViewHolder(item: View): RecyclerView.ViewHolder(item) {

    }
}