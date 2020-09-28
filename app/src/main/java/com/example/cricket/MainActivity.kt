package com.example.cricket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val btnSix =findViewById<Button>(R.id.btnSix)
        val btnFour =findViewById<Button>(R.id.btnFour)
        val btnZero =findViewById<Button>(R.id.btnZero)
        val btn3singles =findViewById<Button>(R.id.btn3singles)
        val btn2singles =findViewById<Button>(R.id.btn2singles)
        val btn1singles =findViewById<Button>(R.id.btn1singles)
        val textScore = findViewById<TextView>(R.id.textScore)
        val textRawScore = findViewById<TextView>(R.id.textRawScore)
        val textOvers = findViewById<TextView>(R.id.textOvers)
        val textBalls = findViewById<TextView>(R.id.textBalls)

        class BallDataFrame constructor(
            var run: Int = 0,
            var batsmanName: String = "",
            var bowlerName: String = "",
            var wicketStatus: String = "",
            var oversBall: Float = 0f,
            var ballLegalExtra: String = ""
        )

        var ballFrameList = mutableListOf<BallDataFrame>()

        var globalNumBalls: Int = 0;
        var globalNumOvers: Int = 0;

        fun buttonClickService(btnValue: Int) {
            ballFrameList.add(BallDataFrame(btnValue,"John", "Gayle", "not out", 0.1f, "le"))
            textScore.text = (textScore.text.toString().toInt() + ballFrameList.last().run).toString()
            textRawScore.text = textRawScore.text.toString() + btnValue.toString()
            globalNumBalls = globalNumBalls.inc()
            //textBalls.text = (globalNumBalls).toString()
            textBalls.text = (ballFrameList[0].run).toString()
            if (globalNumBalls%6==0){
                globalNumOvers = globalNumOvers.inc()
                textOvers.text = (globalNumOvers).toString()
                textRawScore.text = textRawScore.text.toString() + "|"
            }
        }

        //handle button click
        btnSix.setOnClickListener{
            buttonClickService(6)
        }

        //handle button click
        btnFour.setOnClickListener{
            buttonClickService(4)
        }

        //handle button click
        btnZero.setOnClickListener{
            buttonClickService(0)
        }

        //handle button click
        btn3singles.setOnClickListener{
            buttonClickService(3)
        }

        //handle button click
        btn2singles.setOnClickListener{
            buttonClickService(2)
        }

        //handle button click
        btn1singles.setOnClickListener{
            buttonClickService(1)
        }


    }

}
