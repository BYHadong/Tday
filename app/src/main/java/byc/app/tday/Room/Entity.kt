package byc.app.tday.Room

import androidx.room.*

@Entity(tableName = "work_model")
data class WorkModel(
    @PrimaryKey(autoGenerate = true)
    var workId: Long,

    var category: String,

    var workStartTime: String,

    var workFinishTime: String,

    var workMemo: String,

    var workPosition: String
) {
    constructor(
        category: String,
        workStartTime: String,
        workFinishTime: String,
        workMemo: String,
        workPosition: String
    ) : this(0, category, workStartTime, workFinishTime, workMemo, workPosition)
}

@Entity(tableName = "money_table")
data class MoneyModel(
    @PrimaryKey(autoGenerate = true)
    var moneyId: Long,

    var memo: String,

    var value: String,

    var workId: Long
) {
    constructor(memo: String, value: String) : this(0, memo, value, 0)
}

data class WorkWithMoneyModel(
    @Embedded var workModel: WorkModel,
    @Relation(
        parentColumn = "workId",
        entityColumn = "workId"
    )
    val moneyList: List<MoneyModel>
)