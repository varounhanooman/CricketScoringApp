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

        val btnAddBatsman = findViewById<Button>(R.id.btnAddBatsman)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val batsmanName = findViewById<TextView>(R.id.batsmanName)
        var nameBatsmenList = ArrayList<String>()

        //handle button click
        btnAddBatsman.setOnClickListener{
            val tv_dynamic = TextView(this)
            tv_dynamic.textSize = 20f
            tv_dynamic.text = batsmanName.text.toString()
            nameBatsmenList.add(batsmanName.text.toString())
            batsmanName.text = ""
            batsmanName.hint = "Enter Batsman Name"
            val layout = findViewById<LinearLayout>(R.id.linearLayoutNames)
            layout.addView(tv_dynamic)
        }

        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putStringArrayListExtra("BatsmanList", nameBatsmenList)
            startActivity(intent)
        }

    }
}
