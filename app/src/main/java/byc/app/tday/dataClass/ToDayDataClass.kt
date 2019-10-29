package byc.app.tday.dataClass

data class ToDay(
    val toDay : String,	//형식 : Sun 2019/08/28
    val toDayWillDoWork : ToDayWillDoWork,
    val toDayWork : ToDayWork,
    val toDayMoney : ToDayMoney)

data class ToDayWillDoWork(
    val toDayWillDoWork : String,
    val toDayWillDoStudy : String)

data class ToDayMoney(
    val toDayUseMoney : Int,
    val toDayUseMoneyCategory : String,
    val toDayUseMoneyMemo : String)

data class ToDayWork(
    val toDayWorkContent : String,
    val toDayWorkStartTime : String,
    val toDayWorkFinishTime : String)

