package byc.app.tday.Room.Work

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_model")
data class WorkModel (
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null,

    @ColumnInfo(name = "work_category")
    var category: String = "",

    @ColumnInfo(name = "use_work_time")
    var use_work_time: String = "",

    @ColumnInfo(name = "work_memo")
    var work_memo: String? = ""
){
    constructor(category: String, use_work_time: String, work_memo: String?) : this()
    constructor(): this(id = null, "","","")
}