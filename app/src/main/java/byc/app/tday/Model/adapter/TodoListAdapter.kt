package byc.app.tday.Model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import byc.app.tday.R
import byc.app.tday.Room.Work.WorkModel

class TodoListAdapter internal constructor(context: Context): RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var todoList = emptyList<WorkModel>()

    inner class TodoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val workContent: TextView = itemView.findViewById(R.id.WorkContentTodoListItem)
        val workCategory: TextView = itemView.findViewById(R.id.workCategoryTextViewTodoItem)
        val workUseTime: TextView = itemView.findViewById(R.id.WorkUseTimeDataTodoListItem)
        val workDataId: TextView = itemView.findViewById(R.id.workDataId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder = TodoListViewHolder(inflater.inflate(R.layout.todo_list_item, parent, false))

    override fun onBindViewHolder(holder: TodoListAdapter.TodoListViewHolder, position: Int) {
        val workData = todoList[position]
        holder.workDataId.text = workData.id.toString()
        holder.workCategory.text = workData.category
        holder.workContent.text = workData.work_memo
        holder.workUseTime.text = workData.use_work_time
    }

    override fun getItemCount(): Int = todoList.size

    internal fun setTodoList(todoList: List<WorkModel>){
        this.todoList = todoList
        notifyDataSetChanged()
    }
}