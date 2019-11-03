package byc.app.tday.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import byc.app.tday.dataClass.ToDay


public class TDayDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATA_BASE_NAEM, null, DATA_BASE_VERSON){


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_TABLE_CRAET)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_TABLE_CRAET)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun InserData(toDay: ToDay){
        val dataBase = writableDatabase
        val toDayData = ContentValues().apply {
            put(TO_DAY_WILL_DO_WORK_COMMENT, toDay.toDayWillDoWork.toDayWillDoWork)
            put(TO_DAY_WILL_DO_STUDY_COMMENT, toDay.toDayWillDoWork.toDayWillDoStudy)
            put(TO_DAY_WORK_COMMENT, toDay.toDayWork.toDayWorkContent)
            put(TO_DAY_WORK_START_TIME, toDay.toDayWork.toDayWorkStartTime)
            put(TO_DAY_WORK_FINISH_TIME, toDay.toDayWork.toDayWorkFinishTime)
            put(TO_DAY_USE_MONEY_CATEGORY, toDay.toDayMoney.toDayUseMoneyCategory)
            put(TO_DAY_USE_MONEY, toDay.toDayMoney.toDayUseMoney)
            put(TO_DAY_USE_MONEY_MEMO, toDay.toDayMoney.toDayUseMoneyMemo)
        }
        dataBase.insert(DATA_BASE_TABLE_NAME, null, toDayData)
        dataBase.close()
    }

    companion object{
        val DATA_BASE_TABLE_NAME = "ToDay"
        val TO_DAY_WILL_DO_WORK_COMMENT = "toDayWillDoWorkComment"
        val TO_DAY_WILL_DO_STUDY_COMMENT = "toDayWillDoStydtComment"
        val TO_DAY_WORK_COMMENT = "toDayWorkContent"
        val TO_DAY_WORK_START_TIME = "toDayWorkStartTime"
        val TO_DAY_WORK_FINISH_TIME = "toDayWorkFinishTime"
        val TO_DAY_USE_MONEY_CATEGORY = "toDayUseMoneyCategory"
        val TO_DAY_USE_MONEY = "toDayUseMoney"
        val TO_DAY_USE_MONEY_MEMO = "toDayUseMoneyMemo"
        val DATA_BASE_VERSON = 1
        val DATA_BASE_NAEM = "Tday.db"
        val SQL_TABLE_CRAET = "CREAT TABLE $DATA_BASE_TABLE_NAME(" +
                "toDayId Integer PRIMARY KEY," +
                "$TO_DAY_WILL_DO_WORK_COMMENT Text," +
                "$TO_DAY_WILL_DO_STUDY_COMMENT Text," +
                "$TO_DAY_WORK_COMMENT Text," +
                "$TO_DAY_WORK_START_TIME Text," +
                "$TO_DAY_WORK_FINISH_TIME Text," +
                "$TO_DAY_USE_MONEY_CATEGORY Text," +
                "$TO_DAY_USE_MONEY Text," +
                "$TO_DAY_USE_MONEY_MEMO Text)"
        val DATA_BASE_DELET = "DROP TABLE IF EXISTS ${DATA_BASE_TABLE_NAME}"
    }
}