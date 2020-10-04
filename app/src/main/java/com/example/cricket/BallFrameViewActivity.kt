package com.example.cricket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class BallFrameViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball_frame_view)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnRead = findViewById<TextView>(R.id.btnRead)
        val btnClear = findViewById<TextView>(R.id.btnClear)
        val btnBack = findViewById<TextView>(R.id.btnBack)

        val context = this
        var db = DataBaseHandler(context)

        btnRead.setOnClickListener{

            var data = db.readData()
            tvResult.text = ""
            for (i in 0 until data.size){
                tvResult.append(
                        //"Match: " + data.get(i).matchId.toString() + " " +
                        "Runs: " + data.get(i).run.toString() + " " +
                        "Batsman: " + data.get(i).batsmanName + " " +
                        "Bowler: " + data.get(i).bowlerName + " " +
                        "Wicket: " + data.get(i).wicketStatus + " " +
                        "Overs: " + data.get(i).oversBall.toString() + " "
                        //+ "illegal: " + data.get(i).ballLegalExtra + "\n"
                )
            }

        }

        btnClear.setOnClickListener{
            db.deleteData()
            btnRead.performClick()
        }

        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}