package byc.app.tday.Room

import androidx.room.*

@Entity(tableName = "work_model")
data class WorkModel(
    @PrimaryKey(autoGenerate = true)
    var workId: Long?,

    @ColumnInfo(name = "work_category")
    var category: String = "",

    @ColumnInfo(name = "work_start_time")
    var workStartTime: String = "",

    @ColumnInfo(name = "work_finish_time")
    var workFinishTime: String = "",

    @ColumnInfo(name = "work_memo")
    var workMemo: String = "",

    @ColumnInfo(name = "work_position")
    var workPosition: String? = ""
) {
    constructor(category: String, workStartTime: String, workFinishTime: String, workMemo: String, workPosition: String?) : this(
        null,
        category,
        workStartTime,
        workFinishTime,
        workMemo,
        workPosition
    )
}

@Entity(tableName = "money_table")
data class MoneyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo(name = "money_memo")
    val memo: String,

    @ColumnInfo(name = "money_value")
    val value: String,

    @ColumnInfo(name = "work_model_id")
    val workId: Long?
) {
    constructor(value: String, memo: String, workId: Long?) : this(
        null,
        value = value,
        memo = memo,
        workId = workId
    )
}

data class WorkWithMoneyModel(
    @ColumnInfo(name = "work_model")
    @Embedded val workModel: WorkModel,
    @Relation(
        parentColumn = "workId",
        entityColumn = "work_model_id"
    )
    @ColumnInfo(name = "money_list")
    val moneyList: List<MoneyModel>
)