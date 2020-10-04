package com.example.cricket

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME = "MyDB"
val TABLE_NAME = "BallDataFrame"
var COL_ID = "id"
var COL_MATCH_ID = "matchId"
var COL_RUN = "run"
var COL_BATSMAN_NAME = "batsmanName"
var COL_BOWLER_NAME = "bowlerName"
var COL_WICKET_STATUS = "wicketStatus"
var COL_OVERS_BALL = "oversBall"
var COL_BALL_LEGAL_EXTRA = "ballLegalExtra"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MATCH_ID + " INTEGER," +
                COL_RUN + " INTEGER," +
                COL_BATSMAN_NAME + " VARCHAR(256)," +
                COL_BOWLER_NAME + " VARCHAR(256)," +
                COL_WICKET_STATUS + " VARCHAR(256)," +
                COL_OVERS_BALL + " FLOAT," +
                COL_BALL_LEGAL_EXTRA + " VARCHAR(256) )";
        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(ballDataFrame : BallDataFrame){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_MATCH_ID,ballDataFrame.matchId)
        cv.put(COL_RUN,ballDataFrame.run)
        cv.put(COL_BATSMAN_NAME,ballDataFrame.batsmanName)
        cv.put(COL_BOWLER_NAME,ballDataFrame.bowlerName)
        cv.put(COL_WICKET_STATUS,ballDataFrame.wicketStatus)
        cv.put(COL_OVERS_BALL,ballDataFrame.oversBall)
        cv.put(COL_BALL_LEGAL_EXTRA,ballDataFrame.ballLegalExtra)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success",Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<BallDataFrame>{
        var list : MutableList<BallDataFrame> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if (result.moveToFirst())
            do {
                var ballDataFrame = BallDataFrame()
                ballDataFrame.matchId = result.getString(result.getColumnIndex(COL_MATCH_ID)).toInt()
                ballDataFrame.run = result.getString(result.getColumnIndex(COL_RUN)).toInt()
                ballDataFrame.batsmanName = result.getString(result.getColumnIndex(COL_BATSMAN_NAME))
                ballDataFrame.bowlerName = result.getString(result.getColumnIndex(COL_BOWLER_NAME))
                ballDataFrame.wicketStatus = result.getString(result.getColumnIndex(COL_WICKET_STATUS))
                ballDataFrame.oversBall = result.getString(result.getColumnIndex(COL_OVERS_BALL)).toFloat()
                ballDataFrame.ballLegalExtra = result.getString(result.getColumnIndex(COL_BALL_LEGAL_EXTRA))
                list.add(ballDataFrame)
            } while (result.moveToNext())

        result.close()
        db.close()
        return list
    }

}
