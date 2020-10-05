package com.example.cricket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class NameList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_list)

        val context = this
        var homeTeamList = mutableListOf<HomeTeamData>()
        var homePlayerNameList = mutableListOf<String>()

        val btnAddPlayer = findViewById<Button>(R.id.btnAddPlayer)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val playerName = findViewById<TextView>(R.id.playerName)
        
        val playerList = findViewById<ListView>(R.id.listViewPlayerNames)
        
        var db = DataBaseHandler(context)

        var data = db.readDataTeamData()

        //Populate list before letting the user add new names
        for (i in 0 until data.size){
            homePlayerNameList.add(data[i].firstName)
        }
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, homePlayerNameList)
        playerList.adapter = adapter

        //handle button click
        btnAddPlayer.setOnClickListener{

            val newName = playerName.text.toString()
            homeTeamList.add(HomeTeamData(newName, 0, 0, 0, 0))
            db.insertDataTeamData(homeTeamList.last())
            //
            // val tv_dynamic = TextView(this)
            // tv_dynamic.textSize = 20f
            // tv_dynamic.text = playerName.text.toString()

            //add player name to the player list
            //homePlayerNameList.add(data[data.size].firstName)
            //add update adapter for list view
            adapter.add(data[data.size].firstName)
            //playerList.adapter = adapter

            playerName.text = ""
            playerName.hint = "Enter Player Name"
            // layout.addView(tv_dynamic)
        }

        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
