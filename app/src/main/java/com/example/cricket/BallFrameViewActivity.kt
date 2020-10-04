package com.example.cricket

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

        val context = this
        var db = DataBaseHandler(context)

        btnRead.setOnClickListener{

            var data = db.readData()
            tvResult.text = ""
            for (i in 0 until data.size-1){
                tvResult.append(data.get(i).matchId.toString() + " " +
                        data.get(i).run.toString() + " " +
                        data.get(i).batsmanName + " " +
                        data.get(i).bowlerName + " " +
                        data.get(i).wicketStatus + " " +
                        data.get(i).oversBall.toString() + " " +
                        data.get(i).ballLegalExtra + "\n"
                )
            }

        }
    }
}