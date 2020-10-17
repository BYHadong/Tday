package byc.app.tday.Model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.Room.WorkWithMoneyModel

class TdayListAdapter internal constructor(context: Context): RecyclerView.Adapter<TdayListAdapter.TodoListViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var todoList = emptyList<WorkWithMoneyModel>()

    inner class TodoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val workCategory: TextView = itemView.findViewById(R.id.workCategoryTodoListItem)
        val workContent: TextView = itemView.findViewById(R.id.workContentTodoListItem)
        val workStartTime: TextView = itemView.findViewById(R.id.workStartTimeDataTodoListItem)
        val workFinishTime: TextView = itemView.findViewById(R.id.workFinishTimeDataTodoListItem)
        val workPosition: TextView = itemView.findViewById(R.id.workPositionTodoListItem)
        val workUseTime: TextView = itemView.findViewById(R.id.workUseTimeDataTodoListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder = TodoListViewHolder(inflater.inflate(R.layout.todo_list_item, parent, false))

    override fun onBindViewHolder(holder: TdayListAdapter.TodoListViewHolder, position: Int) {
        val workData = todoList[position]
        holder.workCategory.text = workData.workModel.category
        holder.workContent.text = workData.workModel.workMemo
        holder.workUseTime.text = WorkUseTimeCal(workData.workModel.workStartTime, workData.workModel.workFinishTime)
        holder.workStartTime.text = workData.workModel.workStartTime
        holder.workFinishTime.text = workData.workModel.workFinishTime
        holder.workPosition.text = workData.workModel.workPosition

    }

    override fun getItemCount(): Int = todoList.size

    internal fun setTodoList(todoList: List<WorkWithMoneyModel>){
        this.todoList = todoList
        notifyDataSetChanged()
    }

    fun WorkUseTimeCal(startTime: String, finishTime: String): String{
        val start = startTime.split(':')
        val finish = finishTime.split(':')
        val startHour = start.get(0).toInt()
        val startMinute = start.get(1).toInt()
        val finishHour = finish.get(0).toInt()
        val finishMinute = finish.get(1).toInt()

        val useTime = ((startHour * 60) + startMinute) - ((finishHour * 60) + finishMinute)
        val useTimeHour = useTime % 60
        val useTimeMinute = (useTime - (useTimeHour * 60))

        return "${useTimeHour}:${useTimeMinute}"
    }
}