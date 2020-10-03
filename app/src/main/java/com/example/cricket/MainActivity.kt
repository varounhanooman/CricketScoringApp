package com.example.cricket

import android.content.Intent
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
        val textExtras = findViewById<TextView>(R.id.textExtras)

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
        var globalNumWickets: Int = 0;
        var globalNumExtras: Int = 0;


        //Calculate Legal Runs


        val defaulName: String = "DefaultBatsman"

        var batsmanArray: ArrayList<String> = arrayListOf("Test")

        if (intent.getStringArrayListExtra("BatsmanList") != null){
            val myArray: ArrayList<String> = intent.getStringArrayListExtra("BatsmanList")
            //batsmanArray = myArray.toArray()
            System.out.println(myArray);

        }



        fun buttonClickService(btnValue: Int) {
            if (intent.getStringArrayListExtra("BatsmanList") != null){
                ballFrameList.add(BallDataFrame(btnValue,batsmanArray.last(), "Gayle", "not out", 0.1f, "le"))
            }else{
                ballFrameList.add(BallDataFrame(btnValue,defaulName, "Gayle", "not out", 0.1f, "le"))
            }
            textScore.text = (textScore.text.toString().toInt() + ballFrameList.last().run).toString()
            textScore.text = batsmanArray[0]
            textRawScore.text = textRawScore.text.toString() + btnValue.toString()
            globalNumBalls = globalNumBalls.inc()
            textBalls.text = (globalNumBalls).toString()
            //textBalls.text = (ballFrameList[0].run).toString()
            if (globalNumBalls%6==0){
                globalNumOvers = globalNumOvers.inc()
                textOvers.text = (globalNumOvers).toString()
                textRawScore.text = textRawScore.text.toString() + "|"
            }

        }

        //Calculate Wickets
        fun btnWicketClickService(btnHowOut: String){
            globalNumBalls = globalNumBalls.inc()
            textBalls.text = (globalNumBalls).toString()
            textRawScore.text = textRawScore.text.toString() + "W"
            if(btnHowOut.equals("bowled",true)){
                globalNumWickets = globalNumWickets.inc()
                textWickets.text = globalNumWickets.toString()
            }
            else if(btnHowOut.equals("caught",true)){
                globalNumWickets = globalNumWickets.inc()
                textWickets.text = globalNumWickets.toString()
            }
            else if(btnHowOut.equals("run out",true)){
                globalNumWickets = globalNumWickets.inc()
                textWickets.text = globalNumWickets.toString()
            }

            if (globalNumBalls%6==0){
                globalNumOvers = globalNumOvers.inc()
                textOvers.text = (globalNumOvers).toString()
                textRawScore.text = textRawScore.text.toString() + "|"
            }
        }

        //Calculate Extras
        fun btnExtrasClickService(btnExtra: String){
            globalNumExtras = globalNumExtras.inc()
            textExtras.text = globalNumExtras.toString()
            textScore.text = (textScore.text.toString().toInt() + 1).toString()
            if(btnExtra.equals("wide", true)){
                textRawScore.text = textRawScore.text.toString() + "wd"
            }
            else if(btnExtra.equals("no ball", true)) {
                textRawScore.text = textRawScore.text.toString() + "nb"
            }
        }

        fun btnByesClickService(btnBye: String){
            globalNumExtras = globalNumExtras.inc()
            textExtras.text = globalNumExtras.toString()
            textScore.text = (textScore.text.toString().toInt() + 1).toString()
            if(btnBye.equals("leg bye", true)){
                globalNumBalls = globalNumBalls.inc()
                textBalls.text = (globalNumBalls).toString()
                textRawScore.text = textRawScore.text.toString() + "lb"
            }
            else if(btnBye.equals("bye", true)){
                globalNumBalls = globalNumBalls.inc()
                textBalls.text = (globalNumBalls).toString()
                textRawScore.text = textRawScore.text.toString() + "b"
            }

            if (globalNumBalls%6==0){
                globalNumOvers = globalNumOvers.inc()
                textOvers.text = (globalNumOvers).toString()
                textRawScore.text = textRawScore.text.toString() + "|"
            }

        }

        //Handle button click for runs
        //No Runs
        btn0runs.setOnClickListener{
            btnRunsClickService(0)
        }
        //1 Run
        btn1run.setOnClickListener{
            btnRunsClickService(1)
        }
        //2 Runs
        btn2runs.setOnClickListener{
            btnRunsClickService(2)
        }
        //3 Runs
        btn3runs.setOnClickListener{
            btnRunsClickService(3)
        }
        //4 Runs
        btn4runs.setOnClickListener{
            btnRunsClickService(4)
        }
        //5 Runs
        btn5runs.setOnClickListener {
            btnRunsClickService(5)
        }
        //6 Runs
        btn6runs.setOnClickListener{
            btnRunsClickService(6)
        }

        //Handle button click for Wickets
        //Bowled
        btnBowled.setOnClickListener {
            btnWicketClickService("bowled")
        }
        //Caught
        btnCaught.setOnClickListener {
            btnWicketClickService("caught")
        }
        //RunOut
        btnRunOut.setOnClickListener {
            btnWicketClickService("run out")
        }

        //Handle button click for Extras
        //Wide
        btnWide.setOnClickListener {
            btnExtrasClickService("wide")
        }
        //No Ball
        btnNoBall.setOnClickListener {
            btnExtrasClickService("no ball")
        }
        //Leg Bye
        btnLegBye.setOnClickListener {
            btnByesClickService("leg bye")
        }
        //Bye
        btnBye.setOnClickListener {
            btnByesClickService("bye")
        }

        btnNameList.setOnClickListener{
            val intent = Intent(this, NameList::class.java)
            startActivity(intent)
        }














    }

}
