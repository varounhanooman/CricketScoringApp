package com.example.cricket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class NameList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_list)

        val context = this
        var homeTeamList = mutableListOf<HomeTeamData>()

        val btnAddBatsman = findViewById<Button>(R.id.btnAddBatsman)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val batsmanName = findViewById<TextView>(R.id.batsmanName)
        
        val layout = findViewById<LinearLayout>(R.id.linearLayoutNames)
        
        var db = DataBaseHandler(context)

        

        //handle button click
        btnAddBatsman.setOnClickListener{

            val playerName = batsmanName.text.toString()
            homeTeamList.add(HomeTeamData(playerName, 0, 0, 0, 0))
            db.insertDataTeamData(homeTeamList.last())
            // val tv_dynamic = TextView(this)
            // tv_dynamic.textSize = 20f
            // tv_dynamic.text = batsmanName.text.toString()
            batsmanName.text = ""
            batsmanName.hint = "Enter Batsman Name"

            // layout.addView(tv_dynamic)
        }

        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
