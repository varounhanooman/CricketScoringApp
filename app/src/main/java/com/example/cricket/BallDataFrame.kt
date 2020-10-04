package com.example.cricket

class BallDataFrame{

    var matchId: Int = 0
    var run: Int = 0
    var batsmanName: String = ""
    var bowlerName: String = ""
    var wicketStatus: String = ""
    var oversBall: Float = 0f
    var ballLegalExtra: String = ""

    constructor(matchId:Int,run:Int,batsmanName:String,bowlerName:String,wicketStatus:String,oversBall:Float,ballLegalExtra:String){
        this.matchId = matchId
        this.run = run
        this.batsmanName = batsmanName
        this.bowlerName = bowlerName
        this.wicketStatus = wicketStatus
        this.oversBall = oversBall
        this.ballLegalExtra = ballLegalExtra
    }
}