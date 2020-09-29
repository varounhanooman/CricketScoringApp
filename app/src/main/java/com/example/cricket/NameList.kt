package com.example.cricket

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

        //handle button click
        btnAddBatsman.setOnClickListener{
            val tv_dynamic = EditText(this)
            //tv_dynamic.textSize = 20f
            tv_dynamic.hint = "Enter Batsman Name"
            val layout = findViewById<LinearLayout>(R.id.linearLayoutNames)

            layout.addView(tv_dynamic)
        }

    }
}
