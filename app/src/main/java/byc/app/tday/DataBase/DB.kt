package byc.app.tday.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import byc.app.tday.dataClass.ToDay
import byc.app.tday.dataClass.ToDayMoney
import byc.app.tday.dataClass.ToDayWillDoWork
import byc.app.tday.dataClass.ToDayWork


public class ToDayDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATA_BASE_NAEM, null, DATA_BASE_VERSON){

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
            put(TO_DAY, toDay.toDay)
            put(TO_DAY_WILL_DO_WORK_COMMENT, toDay.toDayWillDoWork.toDayWillDoWork)
            put(TO_DAY_WILL_DO_STUDY_COMMENT, toDay.toDayWillDoWork.toDayWillDoStudy)
            put(TO_DAY_WORK, toDay.toDayWork.toDayWork)
            put(TO_DAY_WORK_START_TIME, toDay.toDayWork.toDayWorkStartTime)
            put(TO_DAY_WORK_FINISH_TIME, toDay.toDayWork.toDayWorkFinishTime)
            put(TO_DAY_USE_MONEY_CATEGORY, toDay.toDayMoney.toDayUseMoneyCategory)
            put(TO_DAY_USE_MONEY, toDay.toDayMoney.toDayUseMoney)
            put(TO_DAY_USE_MONEY_MEMO, toDay.toDayMoney.toDayUseMoneyMemo)
        }
        dataBase.insert(DATA_BASE_TABLE_NAME, null, toDayData)
        dataBase.close()
    }

    fun GetToDayDataAll() : MutableList<ToDay>?{
        val db = readableDatabase
        val cusor = db.query(
            DATA_BASE_TABLE_NAME,
            arrayOf(TO_DAY_ID, TO_DAY,TO_DAY_WILL_DO_WORK_COMMENT, TO_DAY_WILL_DO_STUDY_COMMENT, TO_DAY_WORK, TO_DAY_WORK_START_TIME, TO_DAY_WORK_FINISH_TIME, TO_DAY_USE_MONEY_CATEGORY, TO_DAY_USE_MONEY, TO_DAY_USE_MONEY_MEMO),
            null,
            null,
            null,
            null,
            null
            )
        val toDayDatas = mutableListOf<ToDay>()
        with(cusor){
            while (moveToNext()){
                val toDayId = getInt(getColumnIndexOrThrow(TO_DAY_ID))
                val toDay = getString(getColumnIndexOrThrow(TO_DAY))
                val toDayWillDoWorkComment = getString(getColumnIndexOrThrow(TO_DAY_WILL_DO_WORK_COMMENT))
                val toDayWillDoStudyComment = getString(getColumnIndexOrThrow(TO_DAY_WILL_DO_STUDY_COMMENT))
                val toDayWork = getString(getColumnIndexOrThrow(TO_DAY_WORK))
                val toDayStatTime = getString(getColumnIndexOrThrow(TO_DAY_WORK_START_TIME))
                val toDayFinishTime = getString(getColumnIndexOrThrow(TO_DAY_WORK_FINISH_TIME))
                val toDayUseMoneyCategory = getString(getColumnIndexOrThrow(TO_DAY_USE_MONEY_CATEGORY))
                val toDayUseMoney = getString(getColumnIndexOrThrow(TO_DAY_USE_MONEY))
                val toDayUseMoneyMemo = getString(getColumnIndexOrThrow(TO_DAY_USE_MONEY_MEMO))
                val toDayData = ToDay(toDayId, toDay, ToDayWillDoWork(toDayWillDoWorkComment, toDayWillDoStudyComment), ToDayWork(toDayWork, toDayStatTime, toDayFinishTime), ToDayMoney(toDayUseMoney, toDayUseMoneyCategory, toDayUseMoneyMemo))
                toDayDatas.add(toDayData)
            }
        }
        if (toDayDatas.size != 0)
            return toDayDatas
        return null
    }

    fun GetToDayDataWillWork(): ToDay?{
        val db = readableDatabase
        val cusor = db.query(
            DATA_BASE_TABLE_NAME,
            arrayOf(TO_DAY_ID, TO_DAY, TO_DAY_WILL_DO_WORK_COMMENT, TO_DAY_WILL_DO_STUDY_COMMENT),
            null,
            null,
            null,
            null,
            null
        )
        with(cusor){
            while (moveToLast()){
                val toDayId = getInt(getColumnIndexOrThrow(TO_DAY_ID))
                val toDay = getString(getColumnIndexOrThrow(TO_DAY))
                val toDayWillDoWorkComment = getString(getColumnIndexOrThrow(TO_DAY_WILL_DO_WORK_COMMENT))
                val toDayWillDoStudyComment = getString(getColumnIndexOrThrow(TO_DAY_WILL_DO_STUDY_COMMENT))
                val toDayData = ToDay(toDayId, toDay, ToDayWillDoWork(toDayWillDoWorkComment, toDayWillDoStudyComment))
                return toDayData
            }
        }
        return null
    }

    companion object{
        val DATA_BASE_NAEM = "Tday.db"
        val DATA_BASE_VERSON = 1
        val DATA_BASE_TABLE_NAME = "ToDay"
        val TO_DAY_ID = "toDayId"
        val TO_DAY = "toDay"
        val TO_DAY_WILL_DO_WORK_COMMENT = "toDayWillDoWorkComment"
        val TO_DAY_WILL_DO_STUDY_COMMENT = "toDayWillDoStydtComment"
        val TO_DAY_WORK = "toDayWork"
        val TO_DAY_WORK_START_TIME = "toDayWorkStartTime"
        val TO_DAY_WORK_FINISH_TIME = "toDayWorkFinishTime"
        val TO_DAY_USE_MONEY_CATEGORY = "toDayUseMoneyCategory"
        val TO_DAY_USE_MONEY = "toDayUseMoney"
        val TO_DAY_USE_MONEY_MEMO = "toDayUseMoneyMemo"
        val SQL_TABLE_CRAET = "CREAT TABLE $DATA_BASE_TABLE_NAME(" +
                "${TO_DAY_ID} Integer AUTOINCREMENT PRIMARY KEY ," +
                "${TO_DAY} Text,"+
                "$TO_DAY_WILL_DO_WORK_COMMENT Text," +
                "$TO_DAY_WILL_DO_STUDY_COMMENT Text," +
                "$TO_DAY_WORK Text," +
                "$TO_DAY_WORK_START_TIME Text," +
                "$TO_DAY_WORK_FINISH_TIME Text," +
                "$TO_DAY_USE_MONEY_CATEGORY Text," +
                "$TO_DAY_USE_MONEY Text," +
                "$TO_DAY_USE_MONEY_MEMO Text)"
        val DATA_BASE_DELET = "DROP TABLE IF EXISTS ${DATA_BASE_TABLE_NAME}"
        val GET_DATA = "SELET * FORM ${DATA_BASE_TABLE_NAME}"
    }
}