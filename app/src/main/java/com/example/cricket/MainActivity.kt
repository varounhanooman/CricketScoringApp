package com.example.cricket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val btn0runs =findViewById<Button>(R.id.btn0runs)
        val btn1run =findViewById<Button>(R.id.btn1run)
        val btn2runs =findViewById<Button>(R.id.btn2runs)
        val btn3runs =findViewById<Button>(R.id.btn3runs)
        val btn4runs =findViewById<Button>(R.id.btn4runs)
        val btn5runs =findViewById<Button>(R.id.btn5runs)
        val btn6runs =findViewById<Button>(R.id.btn6runs)
        val btnBowled = findViewById<Button>(R.id.btnBowled)
        val btnCaught = findViewById<Button>(R.id.btnCaught)
        val btnRunOut = findViewById<Button>(R.id.btnRunOut)
        val btnNoBall = findViewById<Button>(R.id.btnNoBall)
        val btnWide = findViewById<Button>(R.id.btnWide)
        val btnBye = findViewById<Button>(R.id.btnBye)
        val btnLegBye = findViewById<Button>(R.id.btnLegBye)
        val textScore = findViewById<TextView>(R.id.textScore)
        val textRawScore = findViewById<TextView>(R.id.textRawScore)
        val textOvers = findViewById<TextView>(R.id.textOvers)
        val textBalls = findViewById<TextView>(R.id.textBalls)
        val textWickets = findViewById<TextView>(R.id.textWickets)

        var globalNumBalls: Int = 0;
        var globalNumOvers: Int = 0;
        var globalNumWickets: Int = 0;

        //Calculate Legal Runs
        fun btnRunsClickService(btnValue: Int) {
            textScore.text = (textScore.text.toString().toInt() + btnValue).toString()
            textRawScore.text = textRawScore.text.toString() + btnValue.toString()
            globalNumBalls = globalNumBalls.inc()
            textBalls.text = (globalNumBalls).toString()
            if (globalNumBalls%6==0){
                globalNumOvers = globalNumOvers.inc()
                textOvers.text = (globalNumOvers).toString()
                textRawScore.text = textRawScore.text.toString() + "|"
            }
        }

        //Calculate Wickets
        fun btnWicketClickService(btnHowOut: String){
            globalNumBalls = globalNumBalls.inc()
            if(btnHowOut.equals("bowled",true)){

            }
            if(btnHowOut.equals("caught",true)){

            }
            if(btnHowOut.equals("run out",true)){

            }

            if (globalNumBalls%6==0){
                globalNumOvers = globalNumOvers.inc()
                textOvers.text = (globalNumOvers).toString()
                textRawScore.text = textRawScore.text.toString() + "|"
            }
        }

        //Calculate Extras
        fun btnExtrasClickService(BtnExtra: String){

        }

        //handle button click
        btn0runs.setOnClickListener{
            btnRunsClickService(0)
        }

        //handle button click
        btn1run.setOnClickListener{
            btnRunsClickService(1)
        }

        //handle button click
        btn2runs.setOnClickListener{
            btnRunsClickService(2)
        }

        //handle button click
        btn3runs.setOnClickListener{
            btnRunsClickService(3)
        }

        //handle button click
        btn4runs.setOnClickListener{
            btnRunsClickService(4)
        }

        btn5runs.setOnClickListener {
            btnRunsClickService(5)
        }
        //handle button click
        btn6runs.setOnClickListener{
            btnRunsClickService(6)
        }














    }

}
