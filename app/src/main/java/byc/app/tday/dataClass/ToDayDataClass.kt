package byc.app.tday.dataClass

data class ToDay(
    val id: Int = 0,
    val toDay : String = "",	//형식 : Sun 2019/08/28
    val toDayWillDoWork : ToDayWillDoWork = ToDayWillDoWork("",""),
    val toDayWork: ToDayWork = ToDayWork("","",""),
    val toDayMoney: ToDayMoney = ToDayMoney("","",""))

data class ToDayWillDoWork(
    val toDayWillDoWork : String,
    val toDayWillDoStudy : String)

data class ToDayMoney(
    val toDayUseMoney : String,
    val toDayUseMoneyCategory : String,
    val toDayUseMoneyMemo : String)

data class ToDayWork(
    val toDayWork : String,
    val toDayWorkStartTime : String,
    val toDayWorkFinishTime : String)

