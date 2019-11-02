package byc.app.tday.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import byc.app.tday.dataClass.ToDay

val DATA_BASE_VERSON = 1
val DATA_BASE_NAEM = "Tday.db"

class TDayDataBaseHelper(context: Context, val sqlData: String, val tDayList: ToDay) : SQLiteOpenHelper(context, DATA_BASE_NAEM, null, DATA_BASE_VERSON){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlData)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}