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

val HTEAM_TABLE_NAME = "HomeTeamList" 
var HTEAM_COL_PLAYER_ID = "playerId" /*Unique ID Number for each player 
we have to create a home and away team list which led to me creating the table below
this way we can also store less data in this table and we can create a query to get the
player name to display on both the name list and the main scoring screen or wherever
also this way we only store an id in the data frame*/
var HTEAM_COL_FIRST_NAME = "firstName"
//var HTEAM_COL_LAST_NAME = "lastName"
var HTEAM_COL_RSCORED = "runsScored" //runs scored when batting
var HTEAM_COL_NOUT = "notOut" /*whether the batsman is out or not when the team is finished batting. 
For simplicity we can set this to true (1) for the batsmen not out at the end of the overs as well as
the ppl who didnt get to bat yet*/ 
var HTEAM_COL_RCONCEDED = "runsConceded" //The amount of runs the bowler goes for
var HTEAM_COL_WICKETS = "wickets" //wickets taken by the bowler

/* This is an idea for later on... These would be for historical stats
val PLAYER_TABLE_NAME = "Players"
var PLAYER_ID = "playerId"
var PLAYER_FNAME = "firstName"
var PLAYER_LNAME = "lastName"
var PLAYER_MATCHES = "noOfMatches"
var PLAYER_INNINGS = "noOfInnings"
var PLAYER_RSCORED = "ttlRunsScored"
var PLAYER_NOUT = "ttlNotOuts"
var PLAYER_WICKETS = "ttlWickets"
var PLAYER_RCONCEDE = "ttlRunsConceded"
*/
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable1 = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MATCH_ID + " INTEGER," +
                COL_RUN + " INTEGER," +
                COL_BATSMAN_NAME + " VARCHAR(256)," +
                COL_BOWLER_NAME + " VARCHAR(256)," +
                COL_WICKET_STATUS + " VARCHAR(256)," +
                COL_OVERS_BALL + " FLOAT," +
                COL_BALL_LEGAL_EXTRA + " VARCHAR(256) )";
        db?.execSQL(createTable1)

        val createTable2 = "CREATE TABLE " + HTEAM_TABLE_NAME +" (" +
                HTEAM_COL_PLAYER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                HTEAM_COL_FIRST_NAME + " VARCHAR(256)," +
                //HTEAM_COL_LAST_NAME + " VARCHAR(256)," +
                HTEAM_COL_RSCORED + " INTEGER," +
                HTEAM_COL_NOUT + " INTEGER," +
                HTEAM_COL_RCONCEDED + " INTEGER," +
                HTEAM_COL_WICKETS + " INTEGER )";
        db?.execSQL(createTable2)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
/*******************Ball Data Frame Handler***************************************************/
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

    fun updateData() {
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if (result.moveToFirst())
            do {
                var cv = ContentValues()
                cv.put(COL_RUN,(result.getInt(result.getColumnIndex(COL_RUN))+1))
                db.update(TABLE_NAME,cv, COL_ID + "=? AND " + COL_BATSMAN_NAME + " +?",
                arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                    result.getString(result.getColumnIndex(COL_BATSMAN_NAME))))
            } while (result.moveToNext())

        result.close()
        db.close()
    }

    fun deleteData(){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, null, null)
        db.delete(HTEAM_TABLE_NAME, null, null)
    }

    /***************Team List Handler*******************************************************/
    fun insertDataTeamList(homeTeamList : HomeTeamList){
            val db = this.writeableDatabase
            var cv = ContentValues()
            cv.put(HTEAM_COL_PLAYER_ID, homeTeamList.playerId)
            cv.put(HTEAM_COL_FIRST_NAME, homeTeamList.firstName)
            //cv.put(HTEAM_COL_LAST_NAME, homeTeamList.lastName)
            cv.put(HTEAM_COL_RSCORED, homeTeamList.runsScored)
            cv.put(HTEAM_COL_NOUT, homeTeamList.notOut)
            cv.put(HTEAM_COL_RCONCEDED, homeTeamList.runsConceded)
            cv.put(HTEAM_COL_WICKETS, homeTeamList.wickets)
            try {
                var newHomePlayer = db.insert(HTEAM_TABLE_NAME, null, cv)
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }
            catch(Exception e) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
    }

    fun readDataTeamList() : MutableList<HomeTeamList>{
        var list : MutableList<HomeTeamList> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + HTEAM_TABLE_NAME
        val result = db.rawQuery(query,null)
        if (result.moveToFirst())
            do {
                var homeTeamList = HomeTeamList()
                homeTeamList.playerId = result.getString(result.getColumnIndex(HTEAM_COL_PLAYER_ID)).toInt()
                homeTeamList.firstName = result.getString(result.getColumnIndex(HTEAM_COL_FIRST_NAME))
                //homeTeamList.lastName = result.getString(result.getColumnIndex(HTEAM_COL_LAST_NAME))
                homeTeamList.runsScored = result.getString(result.getColumnIndex(HTEAM_COL_RSCORED)).toInt()
                homeTeamList.notOut = result.getString(result.getColumnIndex(HTEAM_COL_NOUT)).toInt()
                homeTeamList.runsConceded = result.getString(result.getColumnIndex(HTEAM_COL_RCONCEDED)).toInt()
                homeTeamList.wickets = result.getString(result.getColumnIndex(HTEAM_COL_WICKETS)).toInt()
                list.add(homeTeamList)
            } while (result.moveToNext())

        result.close()
        db.close()
        return list
    }

}
