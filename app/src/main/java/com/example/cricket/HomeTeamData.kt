package com.example.cricket

class HomeTeamData{

    //var playerId: Int = 0
    var firstName: String = ""
    //var lastName: String = ""
    var runsScored: Int = 0
    var notOut: Int = 0
    var runsConceded: Int = 0
    var wickets: Int = 0

    constructor(firstName:String,/*lastName:String,*/runsScored:Int,notOut:Int,runsConceded:Int,wickets:Int){
        //this.playerId = playerId
        this.firstName = firstName
        //this.lastName = lastName
        this.runsScored = runsScored
        this.notOut = notOut
        this.runsConceded = runsConceded
        this.wickets = wickets
    }

    constructor(){
    }

}


